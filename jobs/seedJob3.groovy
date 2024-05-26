job('example-job-3') {
    description('An example job 3 created with Job DSL')
    scm {
        git('https://github.com/example/repo.git')
    }
    triggers {
        scm('H/15 * * * *')
    }
    steps {
        shell('echo "Hello, World! from Job 3"')
    }
}
