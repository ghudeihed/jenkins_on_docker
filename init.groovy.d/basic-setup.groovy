import jenkins.model.*
import hudson.model.*

println "Initial Jenkins Setup"

def instance = Jenkins.getInstance()

// Set the number of executors
instance.setNumExecutors(2)

// Configure the system message
instance.setSystemMessage("Welcome Jenkins Instance!")

// Save the state
instance.save()
