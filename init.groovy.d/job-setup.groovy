import jenkins.model.*
import javaposse.jobdsl.plugin.ExecuteDslScripts

println "--> Cleaning up existing jobs"

def instance = Jenkins.getInstance()
def jobsDir = new File('/var/jenkins_home/jobs')

// Delete existing jobs
instance.getAllItems(Job.class).each { job ->
    try {
        job.delete()
        println "Deleted job: ${job.name}"
    } catch (Exception e) {
        println "Error deleting job: ${job.name}"
        e.printStackTrace()
    }
}

println "--> Scanning for job DSL scripts in the jobs directory"

if (jobsDir.exists() && jobsDir.isDirectory()) {
    jobsDir.eachFileMatch(~/.+\.groovy$/) { file ->
        println "Processing job DSL script: ${file.name}"
        def jobName = "seed-job-${file.name}"

        try {
            def seedJob = instance.createProject(hudson.model.FreeStyleProject, jobName)
            def dslScript = new ExecuteDslScripts()
            dslScript.setTargets("jobs/${file.name}")
            dslScript.setUseScriptText(false)
            dslScript.setIgnoreExisting(false)

            seedJob.getBuildersList().add(dslScript)
            seedJob.save()
            println "Created job: ${jobName}"
        } catch (Exception e) {
            println "Error creating job: ${jobName}"
            e.printStackTrace()
        }
    }
    println "--> Job setup completed"
} else {
    println "No jobs directory found or it is not a directory."
}
