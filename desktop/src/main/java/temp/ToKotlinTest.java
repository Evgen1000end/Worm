package temp;

import javafx.beans.value.ChangeListener;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.function.Consumer;

/**
 * Created by demkinev on 28.08.2016.
 */
public class ToKotlinTest {

  static ChangeListener<String> listener;

  public static void main(String[] args) {

    listener = (a, b, c) -> { System.out.println(a+b+c); };


    final Button playButton = new Button(">");


  }



}
