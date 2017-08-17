package test.com.hand.test;

import com.hand.test.ArrayListDemo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

/**
 * ArrayListDemo Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ʮһ�� 22, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ArrayListDemoTest {

    ArrayList a1 = new ArrayList();
    ArrayList a2 = new ArrayList();
    ArrayListDemo arrayListDemo;
    @Before
    public void before() throws Exception {
        arrayListDemo = new ArrayListDemo();
    }

    @After
    public void after() throws Exception {
        arrayListDemo = null;
    }

    /**
     * Method: doArrayList()
     */
//    @Test
//    public void testDoArrayList() throws Exception {
//    //TODO: Test goes here...
//        ArrayList o_userOIDs = new ArrayList();
//        ArrayList n_userOIDs = new ArrayList();
//        o_userOIDs.add("A");
//        o_userOIDs.add("B");
//        o_userOIDs.add("C");
//        o_userOIDs.add("D");
//
//        n_userOIDs.add("A");
//        n_userOIDs.add("B");
//        n_userOIDs.add("F");
//        n_userOIDs.add("D");
//
//
////        arrayListDemo.doArrayList(o_userOIDs,n_userOIDs);
//        Assert.assertEquals("逻辑错误", arrayListDemo.doArrayList(o_userOIDs,n_userOIDs),1);
//    }


//    @Test
//    public void testFindDiff() throws Exception {
//        a1.add("A");
//        a1.add("B");
//        a1.add("C");
//        a1.add("D");
//
//        a2.add("A");
//        a2.add("C");
//        a2.add("E");
//        a2.add("F");
//
//        ArrayList a3 = arrayListDemo.findDiff(a1,a2);
//        ArrayList a4 = arrayListDemo.findDiff(a2,a1);
//    }

    @Test
    public void testFindDiff() throws Exception{
        arrayListDemo.findDiff();
    }
} 
