void call() {
    if rollback_enabled {
        if (rollback_branch != null) {
            Map scmVars = checkout([
                    scm: [$class: 'Git'],
                    credentialsId:  scm.userRemoteConfigs[0].credentialsId
                    branches: [[name: branch.startsWith('refs/heads/') ? branch : "refs/heads/${rollback_branch}"]],
            ])
        } else {
            Map scmVars = checkout scm
        }
    env.GIT_COMMIT = scmVars.GIT_COMMIT
    env.GIT_PREVIOUS_COMMIT = scmVars.GIT_PREVIOUS_COMMIT
    }
    
}