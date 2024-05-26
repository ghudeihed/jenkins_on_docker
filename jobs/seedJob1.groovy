job('example-job-1') {
    description('An example job 1 created with Job DSL')
    scm {
        git('https://github.com/example/repo.git')
    }
    triggers {
        scm('H/5 * * * *')
    }
    steps {
        shell('echo "Hello, World! from Job 1"')
    }
}
