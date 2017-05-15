package me.minidigger.customentities.components;

import com.artemis.Component;
import com.artemis.annotations.PooledWeaver;

/**
 * Example component that just has one little variable
 */
@PooledWeaver
public class ExampleComponent extends Component {

    public long age = 0;
}
