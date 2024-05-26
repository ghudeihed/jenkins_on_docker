import jenkins.model.*
import hudson.model.*
import hudson.slaves.*
import jenkins.slaves.*

println "--> Configuring Jenkins with custom setup"

def instance = Jenkins.getInstance()

// Set the number of executors
instance.setNumExecutors(2)

// Configure the system message
instance.setSystemMessage("Welcome to your custom Jenkins instance!")

// Save the state
instance.save()

// Add slave nodes
def nodes = [
    [name: 'slave1', description: 'Slave Node 1', remoteFS: '/home/jenkins', numExecutors: 1],
    [name: 'slave2', description: 'Slave Node 2', remoteFS: '/home/jenkins', numExecutors: 1],
    [name: 'slave3', description: 'Slave Node 3', remoteFS: '/home/jenkins', numExecutors: 1]
]

nodes.each { node ->
    try {
        def launcher = new JNLPLauncher()
        def slave = new DumbSlave(
            node.name,
            node.description,
            node.remoteFS,
            node.numExecutors.toString(),
            Node.Mode.NORMAL,
            '',
            launcher,
            new RetentionStrategy.Always(),
            []
        )
        instance.addNode(slave)
        println "Successfully added node: ${node.name}"
    } catch (Exception e) {
        println "Error adding node: ${node.name}"
        e.printStackTrace()
    }
}
