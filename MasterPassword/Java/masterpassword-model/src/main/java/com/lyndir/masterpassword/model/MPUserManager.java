package com.lyndir.masterpassword.model;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Maps;
import java.util.*;


/**
 * @author lhunath, 14-12-05
 */
public abstract class MPUserManager {

    private final Map<String, MPUser> usersByName = Maps.newHashMap();

    public MPUserManager(final Iterable<MPUser> users) {
        for (MPUser user : users)
            addUser( user );
    }

    public SortedSet<MPUser> getUsers() {
        return FluentIterable.from( usersByName.values() ).toSortedSet( new Comparator<MPUser>() {
            @Override
            public int compare(final MPUser user1, final MPUser user2) {
                return user1.getLastUsed().compareTo( user2.getLastUsed() );
            }
        } );
    }

    public void addUser(final MPUser user) {
        usersByName.put( user.getFullName(), user );
    }
}
