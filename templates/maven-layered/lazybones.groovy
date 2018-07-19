import org.apache.commons.io.FileUtils
import org.apache.commons.io.filefilter.NameFileFilter
import org.apache.commons.io.filefilter.TrueFileFilter
import org.apache.commons.io.filefilter.WildcardFileFilter
import uk.co.cacoethes.util.NameType
import java.util.logging.Level

def logger = getLog()
def props = [:]

if (projectDir.name =~ /\-/) {
    props.project_class_name = transformText(projectDir.name, from: NameType.HYPHENATED, to: NameType.CAMEL_CASE)
} else {
    props.project_class_name = transformText(projectDir.name, from: NameType.PROPERTY, to: NameType.CAMEL_CASE)
}

// project name is hyphenated and lowercase.  MyProject becomes my-project
props.project_name = transformText(props.project_class_name, from: NameType.CAMEL_CASE, to: NameType.HYPHENATED)

//props.project_name = ask("Define value for 'artifactId' [" + props.project_name + "]: ", props.project_name , "artifactId")
//props.project_class_name = ask("Define value for 'className' [" + props.project_class_name + "]: ", props.project_class_name, "className").capitalize()

props.group = ask("Define value for 'group' [com.mikeleitz]: ", "com.mikeleitz", "group")
props.version = ask("Define value for 'version' [0.0.1-SNAPSHOT]: ", "0.0.1-SNAPSHOT", "version")

props.useLombok = ask("Use Lombok for concise code [true]: ", "true", "useLombok")

props.useLombok = "true".equalsIgnoreCase(props.useLombok.trim()) ? true : false

props.base_package = props.group + "." + transformText(props.project_class_name, from: NameType.CAMEL_CASE, to: NameType.PROPERTY).toLowerCase()

logger.log(Level.FINE, "project class: " + props.project_class_name)
logger.log(Level.FINE, "base package: " + props.base_package)
logger.log(Level.FINE, "project name: " + props.project_name)
logger.log(Level.FINE, "artifact id: " + props.project_name)
logger.log(Level.FINE, "group: " + props.group)
logger.log(Level.FINE, "version: " + props.version)

/* Common functions */

def renameFile = { File from, String path ->
    if (from.file) {
        File to = new File(path)
        to.parentFile.mkdirs()
        FileUtils.moveFile(from, to)
    }
}

def packageToPath = { String path ->
    return path.replace(".", '/')
}

def determinePackageFromJavaFile = { File javaFile ->
    String returnValue = FileUtils.readFileToString(javaFile).findAll(/package\s*(\S+)\s*;/) { full, pack ->
        pack
    }

    // For some reason this groovy code is returning these values in brackets
    returnValue = returnValue.substring(1, returnValue.length() - 1)

    return returnValue
}

def determineClassNameFromJavaFile = { File javaFile ->
    String returnValue = FileUtils.readFileToString(javaFile).findAll(/public\s*class\s*(\S+)\s*\{/) { full, cla ->
        return cla
    }

    // For some reason this groovy code is returning these values in brackets
    returnValue = returnValue.substring(1, returnValue.length() - 1)

    return returnValue
}

// To add other options, copy from external dir or delete directories we don't need
// i.e. If have UI for flex, JS/JQUery, or gwt
// Have all 3 directories: ui-flex, ui-jquery, ui-gwt.  Delete all but 1.

def addAop = true
def addSpringBoot = true
def addSpringSecurity = true
def addGitNameAndIgnore = true

def projectName = projectDir.getName()
props.projectName = projectDir.getName()

// Handle all Java files.
def javaFiles = FileUtils.listFiles(projectDir, new WildcardFileFilter("*.java"), TrueFileFilter.INSTANCE)
javaFiles.each {
    File thisFile = it
    String thisFileDir = thisFile.getParent()

    logger.log(Level.FINE, "root file path " + projectDir)
    logger.log(Level.FINE, "this file path " + thisFile)

    File relativeFilePath = projectDir.toPath().relativize(thisFile.toPath()).toFile()

    logger.log(Level.FINE, "Relative path for this java file : " + relativeFilePath.getPath())

    processTemplates relativeFilePath.getPath(), props

    String pack = determinePackageFromJavaFile(thisFile)
    String className = determineClassNameFromJavaFile(thisFile)

    logger.log(Level.FINE, "pack : " + pack)
    logger.log(Level.FINE, "class : " + className)

    directoryPath = thisFile.getParent()

    logger.log(Level.FINE, "new package : $pack")
    logger.log(Level.FINE, "new class : $className")
    logger.log(Level.FINE, "Directory : $directoryPath")

    String newAbsolutePath = thisFileDir + "/" + packageToPath(pack) + "/" + className + ".java"
    logger.log(Level.FINE, "Copying file to path " + newAbsolutePath)

    renameFile(thisFile, newAbsolutePath)
}

// Handle all pom.xml files.
def pomFiles = FileUtils.listFiles(projectDir, new NameFileFilter("pom.xml"), TrueFileFilter.INSTANCE)
pomFiles.each {
    logger.log(Level.FINE, "Found pom : ${it}")

    File thisFile = it

    File relativeFilePath = projectDir.toPath().relativize(thisFile.toPath()).toFile()

    logger.log(Level.FINE, "relative path : ${relativeFilePath}")

    processTemplates relativeFilePath.getPath(), props
}

// Rename all directories to put the project name in front of them
// e.g. api becomes my-project-api
def pre = projectName + "-"
projectDir.eachDir { d ->
    def name = d.getName()

    def newDir = new File(projectDir, "${pre}${name}")
    d.renameTo(newDir)
}