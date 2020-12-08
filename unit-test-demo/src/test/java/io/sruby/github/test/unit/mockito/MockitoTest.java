package io.sruby.github.test.unit.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @description: test
 * @author: sruby
 * @create: 2020-12-08 08:58
 */

public class MockitoTest {
    List mockedList = mock(List.class);
    MockObject mock = mock(MockObject.class);

    @Test
    public void testVerify() {

        mockedList.add("one");
        mockedList.clear();
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void testStubbing() {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing appears before the actual execution
        when(mockedList.get(0)).thenReturn("first");

        // the following prints "first"
        System.out.println(mockedList.get(0));

        // the following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));
    }

    @Test
    public void testWhenThenReturn() {
        //stubbing using built-in anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("element");

        //stubbing using custom matcher (let's say isValid() returns your own matcher implementation):
//        when(mockedList.contains(argThat(isValid()))).thenReturn("element");

        //following prints "element"
        System.out.println(mockedList.get(999));

        //you can also verify using an argument matcher
        verify(mockedList).get(anyInt());

        //argument matchers can also be written as Java 8 Lambdas
//        verify(mockedList).add(argThat(someString -> someString.length() > 5));
    }

    @Test
    public void testDoThrow() {
        doThrow(new RuntimeException()).when(mockedList).clear();

        //following throws RuntimeException:
        Assertions.assertThrows(RuntimeException.class, () -> mockedList.clear());
    }

    @Test
    public void testStubbingConsecutiveCalls() {

        when(mock.someMethod("some arg"))
                .thenThrow(new RuntimeException())
                .thenReturn("foo");

        //First call: throws runtime exception:
        Assertions.assertThrows(RuntimeException.class, () -> mock.someMethod("some arg"));

        //Second call: prints "foo"
        System.out.println(mock.someMethod("some arg"));

        //Any consecutive call: prints "foo" as well (last stubbing wins).
        System.out.println(mock.someMethod("some arg"));
    }

    @Test
    public void testAnswer(){
        when(mock.someMethod(anyString())).thenAnswer(
                new Answer() {
                    public Object answer(InvocationOnMock invocation) {
                        Object[] args = invocation.getArguments();
                        Object mock = invocation.getMock();
                        return "called with arguments: " + Arrays.toString(args);
                    }
                });

        //Following prints "called with arguments: [foo]"
        System.out.println(mock.someMethod("foo"));
    }

    public class MockObject {
        public String someMethod(String some_arg) {
            return some_arg;
        }
    }

    @Test
    public void testSpy(){
        List list = new LinkedList();
        List spy = spy(list);

        //optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);

        //using the spy calls *real* methods
        spy.add("one");
        spy.add("two");

        //prints "one" - the first element of a list
        System.out.println(spy.get(0));

        //size() method was stubbed - 100 is printed
        System.out.println(spy.size());

        //optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");
    }

    @Test
    public void testSpy_When(){
        List list = new LinkedList();
        List spy = spy(list);

        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        Assertions.assertThrows(IndexOutOfBoundsException.class,()->when(spy.get(0)).thenReturn("foo"));

        //You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);
    }


}
