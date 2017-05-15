package me.minidigger.customentities.api.components;

import com.artemis.Component;
import com.artemis.annotations.PooledWeaver;

/**
 * Created by Martin on 15/05/2017.
 */
@PooledWeaver
public class BaseComponent extends Component {

    public boolean spawned = false;
}
