job('example-job-2') {
    description('An example job 2 created with Job DSL')
    scm {
        git('https://github.com/example/repo.git')
    }
    triggers {
        scm('H/10 * * * *')
    }
    steps {
        shell('echo "Hello, World! from Job 2"')
    }
}
