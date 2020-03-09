package ru.job4j.stat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class Analise, analysing the information related to changes of users.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 15.08.2019
 */
public class Analise {

    /**
     * Analyzes the changes of users.
     *
     * @param previous, the list of users before changing.
     * @param current,  the list of users after changing.
     * @return the Info object including all information about changes with users.
     */
    public Info diff(List<User> previous, List<User> current) {
        int added = 0;
        int changed = 0;
        Map<Integer, String> prs = previous.stream().collect(Collectors.toMap(User::getId, User::getName));
        for (User user : current) {
            if (prs.containsKey(user.getId())) {
                if (!prs.get(user.getId()).equals(user.getName())) {
                    changed++;
                }
            } else {
                added++;
            }
        }
        int deleted = (previous.size() - current.size()) + added;
        return new Info(added, changed, deleted);
    }

    /**
     * Class User.
     */
    public static class User {

        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return this.id == user.id;
        }

        /**
         * Overriding hashCode() we don't use the name field of the class User,
         * by reason that the field can be changed during the object's life.
         */
        @Override
        public int hashCode() {
            return 31 * Integer.hashCode(this.id);
        }
    }

    /**
     * The inserted class Info, which keeps all information about changes.
     */
    public static class Info {

        private int added;
        private int changed;
        private int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }

        @Override
        public String toString() {
            return String.format(
                    "Added: %s, Deleted: %s, Changed: %s",
                    this.added, this.deleted, this.changed
            );
        }
    }
}
