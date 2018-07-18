import uk.co.cacoethes.util.NameType
import org.apache.commons.io.FileUtils

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

println "project class: " + props.project_class_name
println "base package: " + props.base_package
println "project name: " + props.project_name
println "artifact id: " + props.project_name
println "group: " + props.group
println "version: " + props.version

// To add other options, copy from external dir or delete directories we don't need
// i.e. If have UI for flex, JS/JQUery, or gwt
// Have all 3 directories: ui-flex, ui-jquery, ui-gwt.  Delete all but 1.

def addLombok = true
def addAop = true
def addSpringBoot = true
def addSpringSecurity = true
def addGitNameAndIgnore = true

def projectName = projectDir.getName()

props.projectName = projectName

processTemplates "build.gradle", props

processTemplates "pom.xml", props
processTemplates "api/pom.xml", props
processTemplates "api-impl/pom.xml", props
processTemplates "database/pom.xml", props
processTemplates "portal/pom.xml", props
processTemplates "service/pom.xml", props
processTemplates "test/pom.xml", props
processTemplates "ui/pom.xml", props
processTemplates "util/pom.xml", props

processTemplates "service/src/main/java/Application.java", props
processTemplates "service/src/main/java/HelloWorldController.java", props
processTemplates "api/src/main/java/HelloWorld.java", props

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

println "package path : " + packageToPath(props.base_package)
def servicePath = projectDir.getName() + "/service/src/main/java/" + packageToPath(props.base_package) + "/service"
println "service path : " + servicePath

def serviceStartupFile = new File(projectDir.getName() + "/service/src/main/java/Application.java")

renameFile(serviceStartupFile, servicePath + "/" + props.project_class_name + "Application.java")

println "package path : " + packageToPath(props.base_package)
def controllerPath = projectDir.getName() + "/service/src/main/java/" + packageToPath(props.base_package) + "/controller"
println "controller path : " + servicePath

def controllerFile = new File(projectDir.getName() + "/service/src/main/java/HelloWorldController.java")

renameFile(controllerFile, controllerPath + "/HelloWorldController.java")

println "package path : " + packageToPath(props.base_package)
def domainPath = projectDir.getName() + "/api/src/main/java/" + packageToPath(props.base_package) + "/domain"
println "domain path : " + domainPath

def domainFile = new File(projectDir.getName() + "/api/src/main/java/HelloWorld.java")

renameFile(domainFile, domainPath + "/HelloWorld.java")


def pre = projectName + "-"
projectDir.eachDir { d ->
    def name = d.getName()

    def newDir = new File(projectDir, "${pre}${name}")
    d.renameTo(newDir)
}