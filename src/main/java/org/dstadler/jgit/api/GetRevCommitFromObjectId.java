package org.dstadler.jgit.api;

/*
   Copyright 2013, 2014 Dominik Stadler

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

import org.dstadler.jgit.helper.CookbookHelper;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

import java.io.IOException;

/**
 * Simple snippet which shows how to use RevWalk to iterate over objects
 */
public class GetRevCommitFromObjectId {

    public static void main(String[] args) throws IOException {
        try (Repository repository = CookbookHelper.openJGitCookbookRepository()) {
            Ref head = repository.exactRef("refs/heads/master");
            System.out.println("Found head: " + head);

            // a RevWalk allows to walk over commits based on some filtering that is defined
            try (RevWalk walk = new RevWalk(repository)) {
                RevCommit commit = walk.parseCommit(head.getObjectId());
                System.out.println("Found Commit: " + commit);

                // You can also get the commit for an (abbreviated) SHA
                walk.reset();
                ObjectId id = repository.resolve("38d51408bd");
                RevCommit commitAgain = walk.parseCommit(id);
                System.out.println("Found Commit again: " + commitAgain);

                walk.dispose();
            }
        }
    }
}
