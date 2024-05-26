import jenkins.model.*
import javaposse.jobdsl.plugin.*

println "Creating seed job"

def instance = Jenkins.getInstance()

def seedJob = instance.createProject(hudson.model.FreeStyleProject, 'seed-job')
def dslScript = new javaposse.jobdsl.plugin.ExecuteDslScripts()
dslScript.setTargets('jobs/seedJob.groovy')
dslScript.setUseScriptText(false)
dslScript.setIgnoreExisting(false)

seedJob.getBuildersList().add(dslScript)
seedJob.save()


job('hello-world-job') {
    description('This job was created with Job DSL')
    scm {
        git('https://github.com/ghudeihed/jenkins_on_docker')
    }
    triggers {
        scm('H/5 * * * *')
    }
    steps {
        shell('echo "Hello, World!"')
    }
}
