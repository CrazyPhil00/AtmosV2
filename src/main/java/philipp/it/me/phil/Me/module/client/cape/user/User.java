/**
 * DeveloperCapes by Jadar
 * License: MIT License
 * (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 4.0.0.x
 */
package philipp.it.me.phil.Me.module.client.cape.user;

import philipp.it.me.phil.Me.module.client.cape.cape.ICape;

import java.util.ArrayList;
import java.util.List;

/**
 * This player is getting their own cape
 * 
 * @author jadar
 */
public class User {

    public List<ICape> capes;
    public final String userUUID;

    public User(String userUUID) {
        this.userUUID = userUUID;
        this.capes = new ArrayList<ICape>();
    }
}