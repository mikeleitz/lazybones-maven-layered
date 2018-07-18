import org.apache.commons.io.FileUtils
import org.apache.commons.io.filefilter.NameFileFilter
import org.apache.commons.io.filefilter.WildcardFileFilter
import org.apache.commons.io.filefilter.TrueFileFilter
import uk.co.cacoethes.util.NameType

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

props.base_package = props.group + "." + transformText(props.project_class_name, from: NameType.CAMEL_CASE, to: NameType.PROPERTY).toLowerCase()

//println "project class: " + props.project_class_name
//println "base package: " + props.base_package
//println "project name: " + props.project_name
//println "artifact id: " + props.project_name
//println "group: " + props.group
//println "version: " + props.version

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

def addLombok = true
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

//    println "root file path " + projectDir
//    println "this file path " + thisFile

    File relativeFilePath = projectDir.toPath().relativize(thisFile.toPath()).toFile()

//    println "Relative path for this java file : " + relativeFilePath.getPath()

    processTemplates relativeFilePath.getPath(), props

    String pack = determinePackageFromJavaFile(thisFile)
    String className = determineClassNameFromJavaFile(thisFile)

//    println "pack : " + pack
//    println "class : " + className

    directoryPath = thisFile.getParent()

//    println "new package : $pack"
//    println "new class : $className"
//    println "Directory : $directoryPath"

    String newAbsolutePath = thisFileDir + "/" + packageToPath(pack) + "/" + className + ".java"
//    println "Copying file to path " + newAbsolutePath

    renameFile(thisFile, newAbsolutePath)
}

// Handle all pom.xml files.
def pomFiles = FileUtils.listFiles(projectDir, new NameFileFilter("pom.xml"), TrueFileFilter.INSTANCE)
pomFiles.each {
//    println "Found pom : ${it}"

    File thisFile = it

    File relativeFilePath = projectDir.toPath().relativize(thisFile.toPath()).toFile()

//    println "relative path : ${relativeFilePath}"

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