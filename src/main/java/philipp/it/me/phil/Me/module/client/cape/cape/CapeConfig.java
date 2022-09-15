/**
 * DeveloperCapes by Jadar
 * License: MIT License
 * (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 4.0.0.x
 */
package philipp.it.me.phil.Me.module.client.cape.cape;



import philipp.it.me.phil.Me.module.client.cape.user.Group;
import philipp.it.me.phil.Me.module.client.cape.user.User;

import java.util.HashMap;

/**
 * The players that need to be outfitted are stored here
 * 
 * @author jadar
 */
public class CapeConfig {
    public HashMap<String, Group> groups;
    public HashMap<String, User> users;

    public CapeConfig() {
        groups = new HashMap<String, Group>();
        users = new HashMap<String, User>();
    }
}