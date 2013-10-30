package automath.type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: dannygoodman
 * Date: 10/30/13
 * Time: 1:03 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class OperatorTest {

    @Test
    public void simplePrint() {
        Variable var = new Variable("x");
        NaturalNumber num = new NaturalNumber("4");
        List<BaseType> operands = new ArrayList<BaseType>(2);
        operands.add(var);
        operands.add(num);

        Operator eq = new Operator("=", operands);

        assertThat(eq.toString(), is("x=4"));
    }
}
