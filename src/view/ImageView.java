package view;

import java.io.IOException;
import java.util.Objects;

import model.Project;

/**
 * An implementation of the IImageView interface that takes a project as its model. Represents
 * the text based representation.
 */

public class ImageView implements IImageView {

  private final Project p;
  private final Appendable a;


  /**
   * Constructor that only takes a model and defaults to System.out.
   *
   * @param p the supplied model.
   */

  public ImageView(Project p) {

    Objects.requireNonNull(p);

    this.p = p;
    this.a = System.out;

  }

  /**
   * Constructor that takes a model and an appendable.
   *
   * @param p the supplied model.
   * @param a the supplied appendable.
   * @throws IllegalArgumentException if the model or appendable given is null.
   */

  public ImageView(Project p, Appendable a) {

    if (Objects.isNull(p) || Objects.isNull(a)) {

      throw new IllegalArgumentException("Provided project or appendable is null");
    }

    this.p = p;
    this.a = a;

  }


  @Override
  public void renderMessage(String message) throws IOException {

    this.a.append(message).append("\n");


  }
}
