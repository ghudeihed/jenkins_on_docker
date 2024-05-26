import jenkins.model.*
import hudson.slaves.*

// Add slave nodes
println "--> Adding slave nodes"

def instance = Jenkins.getInstance()

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

println "--> Node setup completed"
