import jenkins.model.*
import javaposse.jobdsl.plugin.ExecuteDslScripts

println "--> Creating seed job"

def instance = Jenkins.getInstance()

def seedJob = instance.createProject(hudson.model.FreeStyleProject, 'seed-job')
def dslScript = new ExecuteDslScripts()
dslScript.setTargets('jobs/seedJob.groovy')
dslScript.setUseScriptText(false)
dslScript.setIgnoreExisting(false)

seedJob.getBuildersList().add(dslScript)
seedJob.save()
println "Seed job created successfully"
