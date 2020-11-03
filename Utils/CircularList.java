package Utils;

import java.util.ArrayList;

public class CircularList <E> extends ArrayList<E> {

  private static final long serialVersionUID = 8403416217783171295L;

    @Override
    public E get(int index) {
      return super.get(index % size());
    }
  }
