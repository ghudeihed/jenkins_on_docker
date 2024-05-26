job('example-job') {
    description('An example job created with Job DSL')
    scm {
        git('https://github.com/example/repo.git')
    }
    triggers {
        scm('H/5 * * * *')
    }
    steps {
        shell('echo "Hello, World!"')
    }
}
