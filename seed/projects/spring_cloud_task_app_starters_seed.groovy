
job('spring-cloud-task-app-starters-seed') {
    triggers {
        githubPush()
    }
    scm {
        git {
            remote {
                github('spring-io/build-scripts')
            }
            branch('master')
        }
    }
    steps {
        gradle("clean build")
        dsl {
            external('jobs/springcloudtaskappstarters/*.groovy')
            removeAction('DISABLE')
            removeViewAction('DELETE')
            ignoreExisting(false)
            additionalClasspath([
                    'src/main/groovy', 'src/main/resources', 'build/lib/*.jar'
            ].join("\n"))
        }
    }
}
