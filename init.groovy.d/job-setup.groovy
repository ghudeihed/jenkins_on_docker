import jenkins.model.*
import javaposse.jobdsl.plugin.ExecuteDslScripts

println "--> Scanning for job DSL scripts in the jobs directory"

def instance = Jenkins.getInstance()
def jobsDir = new File('/var/jenkins_home/jobs')

if (jobsDir.exists() && jobsDir.isDirectory()) {
    jobsDir.eachFileMatch(~/.+\.groovy$/) { file ->
        println "Processing job DSL script: ${file.name}"

        def seedJob = instance.createProject(hudson.model.FreeStyleProject, "seed-job-${file.name}")
        def dslScript = new ExecuteDslScripts()
        dslScript.setTargets("jobs/${file.name}")
        dslScript.setUseScriptText(false)
        dslScript.setIgnoreExisting(false)

        seedJob.getBuildersList().add(dslScript)
        seedJob.save()
    }
    println "--> Job setup completed"
} else {
    println "No jobs directory found or it is not a directory."
}
