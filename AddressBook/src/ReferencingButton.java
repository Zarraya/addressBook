import javax.swing.*;

public class ReferencingButton<T> extends JButton
{
    private T value;

    public T getValue()
    {
        return this.value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }
}