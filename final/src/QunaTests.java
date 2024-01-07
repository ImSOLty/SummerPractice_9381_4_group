import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class QunaTests {

    @Test
    public void runQuna() {
        // 1st test 4 verts in every part
        //Quna algo = new Quna();
        Graph test1 = new Graph();

        Graph.LeftVertex l1 = test1.new LeftVertex("1");
        Graph.LeftVertex l2 = test1.new LeftVertex("2");
        Graph.LeftVertex l3 = test1.new LeftVertex("3");
        Graph.LeftVertex l4 = test1.new LeftVertex("4");
        test1.appendVertex(l1);
        test1.appendVertex(l2);
        test1.appendVertex(l3);
        test1.appendVertex(l4);
        Graph.RightVertex r5 = test1.new RightVertex("5");
        Graph.RightVertex r6 = test1.new RightVertex("6");
        Graph.RightVertex r7 = test1.new RightVertex("7");
        Graph.RightVertex r8 = test1.new RightVertex("8");
        test1.appendVertex(r5);
        test1.appendVertex(r6);
        test1.appendVertex(r7);
        test1.appendVertex(r8);

        Edge e15 = new Edge(l1, r5);
        l1.appendEdge(e15);
        r5.appendEdge(e15);
        test1.appendEdge(e15);

        Edge e18 = new Edge(l1, r8);
        l1.appendEdge(e18);
        r8.appendEdge(e18);
        test1.appendEdge(e18);


        Edge e26 = new Edge(l2, r6);
        l2.appendEdge(e26);
        r6.appendEdge(e26);
        test1.appendEdge(e26);

        Edge e28 = new Edge(l2, r8);
        l2.appendEdge(e28);
        r8.appendEdge(e28);
        test1.appendEdge(e28);

        Edge e36 = new Edge(l3, r6);
        l3.appendEdge(e36);
        r6.appendEdge(e36);
        test1.appendEdge(e36);

        Edge e37 = new Edge(l3, r7);
        l3.appendEdge(e37);
        r7.appendEdge(e37);
        test1.appendEdge(e37);

        Edge e46 = new Edge(l4, r6);
        l4.appendEdge(e46);
        r6.appendEdge(e46);
        test1.appendEdge(e46);

        Edge e25 = new Edge(l2, r5);
        l2.appendEdge(e25);
        r5.appendEdge(e25);
        test1.appendEdge(e25);

        Edge e47 = new Edge(l4, r7);
        l4.appendEdge(e47);
        r7.appendEdge(e47);
        test1.appendEdge(e47);

        Edge e48 = new Edge(l4, r8);
        l4.appendEdge(e48);
        r7.appendEdge(e48);
        test1.appendEdge(e48);

        Quna.graph = test1;
        Quna.algorithm();
        Quna.reserved = test1.getResult();
        Assertions.assertEquals("1", Quna.reserved.get(0).getLeftVertex().getName());
        Assertions.assertEquals("5", Quna.reserved.get(0).getRightVertex().getName());
        Assertions.assertEquals("2", Quna.reserved.get(1).getLeftVertex().getName());
        Assertions.assertEquals("8", Quna.reserved.get(1).getRightVertex().getName());
        Assertions.assertEquals("3", Quna.reserved.get(2).getLeftVertex().getName());
        Assertions.assertEquals("7", Quna.reserved.get(2).getRightVertex().getName());
        Assertions.assertEquals("4", Quna.reserved.get(3).getLeftVertex().getName());
        Assertions.assertEquals("6", Quna.reserved.get(3).getRightVertex().getName());
        //test1.printResult();

        // 2 тест - нуль граф
        Graph test2 = new Graph();
        Graph.LeftVertex la = test2.new LeftVertex("a");
        Graph.LeftVertex lb = test2.new LeftVertex("b");
        Graph.LeftVertex lc = test2.new LeftVertex("c");

        Graph.RightVertex rd = test2.new RightVertex("d");
        Graph.RightVertex re = test2.new RightVertex("e");
        Graph.RightVertex rf = test2.new RightVertex("f");

        test2.appendVertex(la);
        test2.appendVertex(lb);
        test2.appendVertex(lc);
        test2.appendVertex(rd);
        test2.appendVertex(re);
        test2.appendVertex(rf);

        Quna.graph = test2;
        Quna.logger.setLogMes(new StringBuilder());
        Quna.algorithm();
        Quna.reserved = test2.getResult();
        Assertions.assertNull(Quna.graph.getResult());

        // 3 полный граф из 4 вершин в каждой доле
        Graph test3 = new Graph();
        Graph.LeftVertex u1 = test3.new LeftVertex("u1");
        Graph.LeftVertex u2 = test3.new LeftVertex("u2");
        Graph.LeftVertex u3 = test3.new LeftVertex("u3");
        Graph.LeftVertex u4 = test3.new LeftVertex("u4");
        Graph.RightVertex v1 = test3.new RightVertex("v1");
        Graph.RightVertex v2 = test3.new RightVertex("v2");
        Graph.RightVertex v3 = test3.new RightVertex("v3");
        Graph.RightVertex v4 = test3.new RightVertex("v4");

        test3.appendVertex(u1);
        test3.appendVertex(u2);
        test3.appendVertex(u3);
        test3.appendVertex(u4);
        test3.appendVertex(v1);
        test3.appendVertex(v2);
        test3.appendVertex(v3);
        test3.appendVertex(v4);

        Edge e11 = new Edge(u1, v1);
        u1.appendEdge(e11);
        v1.appendEdge(e11);
        test3.appendEdge(e11);

        Edge e12 = new Edge(u1, v2);
        u1.appendEdge(e12);
        v2.appendEdge(e12);
        test3.appendEdge(e12);

        Edge e13 = new Edge(u1, v3);
        u1.appendEdge(e13);
        v3.appendEdge(e13);
        test3.appendEdge(e13);

        Edge e14 = new Edge(u1, v4);
        u1.appendEdge(e14);
        v4.appendEdge(e14);
        test3.appendEdge(e14);

        Edge e21 = new Edge(u2, v1);
        u2.appendEdge(e21);
        v1.appendEdge(e21);
        test3.appendEdge(e21);

        Edge e22 = new Edge(u2, v2);
        u2.appendEdge(e22);
        v2.appendEdge(e22);
        test3.appendEdge(e22);

        Edge e23 = new Edge(u2, v3);
        u2.appendEdge(e23);
        v3.appendEdge(e23);
        test3.appendEdge(e23);

        Edge e24 = new Edge(u2, v4);
        u2.appendEdge(e24);
        v4.appendEdge(e24);
        test3.appendEdge(e24);

        Edge e31 = new Edge(u3, v1);
        u3.appendEdge(e31);
        v1.appendEdge(e31);
        test3.appendEdge(e31);

        Edge e32 = new Edge(u3, v2);
        u3.appendEdge(e32);
        v2.appendEdge(e32);
        test3.appendEdge(e32);

        Edge e33 = new Edge(u3, v3);
        u3.appendEdge(e33);
        v3.appendEdge(e33);
        test3.appendEdge(e33);

        Edge e34 = new Edge(u3, v4);
        u3.appendEdge(e34);
        v4.appendEdge(e34);
        test3.appendEdge(e34);

        Edge e41 = new Edge(u4, v1);
        u4.appendEdge(e41);
        v1.appendEdge(e41);
        test3.appendEdge(e41);

        Edge e42 = new Edge(u4, v2);
        u4.appendEdge(e42);
        v2.appendEdge(e42);
        test3.appendEdge(e42);

        Edge e43 = new Edge(u4, v3);
        u4.appendEdge(e43);
        v3.appendEdge(e43);
        test3.appendEdge(e43);

        Edge e44 = new Edge(u4, v4);
        u4.appendEdge(e44);
        v4.appendEdge(e44);
        test3.appendEdge(e44);

        Quna.graph = test3;
        Quna.logger.setLogMes(new StringBuilder());
        Quna.algorithm();
        Quna.reserved = test3.getResult();
        Assertions.assertEquals("u1", Quna.reserved.get(0).getLeftVertex().getName());
        Assertions.assertEquals("v4", Quna.reserved.get(0).getRightVertex().getName());
        Assertions.assertEquals("u2", Quna.reserved.get(1).getLeftVertex().getName());
        Assertions.assertEquals("v3", Quna.reserved.get(1).getRightVertex().getName());
        Assertions.assertEquals("u3", Quna.reserved.get(2).getLeftVertex().getName());
        Assertions.assertEquals("v2", Quna.reserved.get(2).getRightVertex().getName());
        Assertions.assertEquals("u4", Quna.reserved.get(3).getLeftVertex().getName());
        Assertions.assertEquals("v1", Quna.reserved.get(3).getRightVertex().getName());

        // 4 провизвольный разрежённый граф с большим количеством изолированных вершин
        Graph test4 = new Graph();
        l1 = test4.new LeftVertex("sef");
        l2 = test4.new LeftVertex("mew");
        l3 = test4.new LeftVertex("sd d");
        l4 = test4.new LeftVertex("tg 0.3");

        Graph.RightVertex r1 = test4.new RightVertex("hom");
        Graph.RightVertex r2 = test4.new RightVertex("  ");
        Graph.RightVertex r3 = test4.new RightVertex("ko lpe");
        Graph.RightVertex r4 = test4.new RightVertex("jk ");
        r5 = test4.new RightVertex("yi");
        r6 = test4.new RightVertex("dwd");
        r7 = test4.new RightVertex("pow");

        test4.appendVertex(l1);
        test4.appendVertex(l2);
        test4.appendVertex(l3);
        test4.appendVertex(l4);
        test4.appendVertex(r1);
        test4.appendVertex(r2);
        test4.appendVertex(r3);
        test4.appendVertex(r4);
        test4.appendVertex(r5);
        test4.appendVertex(r6);
        test4.appendVertex(r7);

        e12 = new Edge(l1, r2);
        l1.appendEdge(e12);
        r2.appendEdge(e12);
        test4.appendEdge(e12);

        e47 = new Edge(l4, r7);
        l4.appendEdge(e47);
        r7.appendEdge(e47);
        test4.appendEdge(e47);

        Quna.graph = test4;
        Quna.logger.setLogMes(new StringBuilder());
        Quna.algorithm();
        Quna.reserved = test4.getResult();

        Quna.outputToFile("out.txt");
        Assertions.assertEquals("sef", Quna.reserved.get(0).getLeftVertex().getName());
        Assertions.assertEquals("  ", Quna.reserved.get(0).getRightVertex().getName());
        Assertions.assertEquals("tg 0.3", Quna.reserved.get(1).getLeftVertex().getName());
        Assertions.assertEquals("pow", Quna.reserved.get(1).getRightVertex().getName());

        // 5 произвольный граф

        Graph test5 = new Graph();
        la = test5.new LeftVertex("a");
        lb = test5.new LeftVertex("b");
        lc = test5.new LeftVertex("c");

        rd = test5.new RightVertex("d");
        re = test5.new RightVertex("e");


        test5.appendVertex(la);
        test5.appendVertex(lb);
        test5.appendVertex(lc);
        test5.appendVertex(rd);
        test5.appendVertex(re);


        Edge ead = new Edge(la, rd);
        la.appendEdge(ead);
        rd.appendEdge(ead);
        test5.appendEdge(ead);

        Edge ebd = new Edge(lb, rd);
        lb.appendEdge(ebd);
        rd.appendEdge(ebd);
        test5.appendEdge(ebd);

        Edge ece = new Edge(lc, re);
        lc.appendEdge(ece);
        re.appendEdge(ece);
        test5.appendEdge(ece);

        Quna.graph = test5;
        Quna.logger.setLogMes(new StringBuilder());
        Quna.algorithm();
        Quna.reserved = test5.getResult();
        Assertions.assertEquals("a", Quna.reserved.get(0).getLeftVertex().getName());
        Assertions.assertEquals("d", Quna.reserved.get(0).getRightVertex().getName());
        Assertions.assertEquals("c", Quna.reserved.get(1).getLeftVertex().getName());
        Assertions.assertEquals("e", Quna.reserved.get(1).getRightVertex().getName());

        // 6 произвольный граф
        Graph test6 = new Graph();
        la = test6.new LeftVertex("a");
        lb = test6.new LeftVertex("b");
        lc = test6.new LeftVertex("c");
        Graph.LeftVertex ld = test6.new LeftVertex("d");
        Graph.LeftVertex le = test6.new LeftVertex("e");

        Graph.RightVertex rHello = test6.new RightVertex("Hello");
        Graph.RightVertex rJello = test6.new RightVertex("Jello");
        Graph.RightVertex rMello = test6.new RightVertex("Mello");
        r1 = test6.new RightVertex("1");
        r2 = test6.new RightVertex("2");

        test6.appendVertex(la);
        test6.appendVertex(lb);
        test6.appendVertex(lc);
        test6.appendVertex(ld);
        test6.appendVertex(le);
        test6.appendVertex(rHello);
        test6.appendVertex(rJello);
        test6.appendVertex(rMello);
        test6.appendVertex(r1);
        test6.appendVertex(r2);

        Edge aHello = new Edge(la, rHello);
        la.appendEdge(aHello);
        rHello.appendEdge(aHello);
        test6.appendEdge(aHello);

        Edge bJello = new Edge(lb, rJello);
        lb.appendEdge(bJello);
        rJello.appendEdge(bJello);
        test6.appendEdge(bJello);

        Edge cMello = new Edge(lc, rMello);
        lc.appendEdge(cMello);
        rMello.appendEdge(cMello);
        test6.appendEdge(cMello);

        Edge d1 = new Edge(ld, r1);
        ld.appendEdge(d1);
        r1.appendEdge(d1);
        test6.appendEdge(d1);

        Edge e2 = new Edge(le, r2);
        le.appendEdge(e2);
        r2.appendEdge(e2);
        test6.appendEdge(e2);

        Quna.graph = test6;
        Quna.logger.setLogMes(new StringBuilder());
        Quna.algorithm();
        Quna.reserved = test6.getResult();
        Assertions.assertEquals("a", Quna.reserved.get(0).getLeftVertex().getName());
        Assertions.assertEquals("Hello", Quna.reserved.get(0).getRightVertex().getName());
        Assertions.assertEquals("b", Quna.reserved.get(1).getLeftVertex().getName());
        Assertions.assertEquals("Jello", Quna.reserved.get(1).getRightVertex().getName());
        Assertions.assertEquals("c", Quna.reserved.get(2).getLeftVertex().getName());
        Assertions.assertEquals("Mello", Quna.reserved.get(2).getRightVertex().getName());
        Assertions.assertEquals("d", Quna.reserved.get(3).getLeftVertex().getName());
        Assertions.assertEquals("1", Quna.reserved.get(3).getRightVertex().getName());
        Assertions.assertEquals("e", Quna.reserved.get(4).getLeftVertex().getName());
        Assertions.assertEquals("2", Quna.reserved.get(4).getRightVertex().getName());

        // 7
        Graph test7 = new Graph();
        Graph.LeftVertex lD = test7.new LeftVertex("D");
        Graph.LeftVertex lE = test7.new LeftVertex("E");
        Graph.LeftVertex lF = test7.new LeftVertex("F");
        Graph.LeftVertex lG = test7.new LeftVertex("G");
        Graph.RightVertex rA = test7.new RightVertex("A");
        Graph.RightVertex rB = test7.new RightVertex("B");
        Graph.RightVertex rC = test7.new RightVertex("C");
        test7.appendVertex(lD);
        test7.appendVertex(lE);
        test7.appendVertex(lF);
        test7.appendVertex(lG);
        test7.appendVertex(rA);
        test7.appendVertex(rB);
        test7.appendVertex(rC);

        Edge DA = new Edge(lD, rA);
        lD.appendEdge(DA);
        rA.appendEdge(DA);
        test7.appendEdge(DA);

        Edge DC = new Edge(lD, rC);
        lD.appendEdge(DC);
        rC.appendEdge(DC);
        test7.appendEdge(DC);

        Edge EA = new Edge(lE, rA);
        lE.appendEdge(EA);
        rA.appendEdge(EA);
        test7.appendEdge(EA);

        Edge EB = new Edge(lE, rB);
        lE.appendEdge(EB);
        rB.appendEdge(EB);
        test7.appendEdge(EB);

        Edge EC = new Edge(lE, rC);
        lE.appendEdge(EC);
        rC.appendEdge(EC);
        test7.appendEdge(EC);

        Edge FA = new Edge(lF, rA);
        lF.appendEdge(FA);
        rA.appendEdge(FA);
        test7.appendEdge(FA);

        Edge FB = new Edge(lF, rB);
        lF.appendEdge(FB);
        rB.appendEdge(FB);
        test7.appendEdge(FB);

        Edge FC = new Edge(lF, rC);
        lF.appendEdge(FC);
        rC.appendEdge(FC);
        test7.appendEdge(FC);

        Edge GB = new Edge(lG, rB);
        lG.appendEdge(GB);
        rB.appendEdge(GB);
        test7.appendEdge(GB);

        Quna.graph = test7;
        Quna.logger.setLogMes(new StringBuilder());
        Quna.algorithm();
        Quna.reserved = test7.getResult();
        Assertions.assertEquals("D", Quna.reserved.get(0).getLeftVertex().getName());
        Assertions.assertEquals("C", Quna.reserved.get(0).getRightVertex().getName());
        Assertions.assertEquals("E", Quna.reserved.get(1).getLeftVertex().getName());
        Assertions.assertEquals("B", Quna.reserved.get(1).getRightVertex().getName());
        Assertions.assertEquals("F", Quna.reserved.get(2).getLeftVertex().getName());
        Assertions.assertEquals("A", Quna.reserved.get(2).getRightVertex().getName());

        // 8 звезда
        Graph test8 = new Graph();
        l1 = test8.new LeftVertex("1");
        l2 = test8.new LeftVertex("2");
        l3 = test8.new LeftVertex("3");
        l4 = test8.new LeftVertex("4");
        Graph.LeftVertex l5 =  test8.new LeftVertex("5");
        Graph.LeftVertex l6 =  test8.new LeftVertex("6");
        Graph.RightVertex rS = test8.new RightVertex("S");
        test8.appendVertex(l1);
        test8.appendVertex(l2);
        test8.appendVertex(l3);
        test8.appendVertex(l4);
        test8.appendVertex(l5);
        test8.appendVertex(l6);
        test8.appendVertex(rS);

        Edge S1 = new Edge(l1, rS);
        l1.appendEdge(S1);
        rS.appendEdge(S1);
        test8.appendEdge(S1);

        Edge S2 = new Edge(l2, rS);
        l2.appendEdge(S2);
        rS.appendEdge(S2);
        test8.appendEdge(S2);

        Edge S3 = new Edge(l3, rS);
        l3.appendEdge(S3);
        rS.appendEdge(S3);
        test8.appendEdge(S3);

        Edge S4 = new Edge(l4, rS);
        l4.appendEdge(S4);
        rS.appendEdge(S4);
        test8.appendEdge(S4);

        Edge S5 = new Edge(l5, rS);
        l5.appendEdge(S5);
        rS.appendEdge(S5);
        test8.appendEdge(S5);

        Edge S6 = new Edge(l6, rS);
        l6.appendEdge(S6);
        rS.appendEdge(S6);
        test8.appendEdge(S6);

        Quna.graph = test8;
        Quna.logger.setLogMes(new StringBuilder());
        Quna.algorithm();
        Quna.reserved = test8.getResult();

        Assertions.assertEquals("1", Quna.reserved.get(0).getLeftVertex().getName());
        Assertions.assertEquals("S", Quna.reserved.get(0).getRightVertex().getName());
        // 9 звезда в другую сторону
        Graph test9 = new Graph();
        Graph.LeftVertex lS = test9. new LeftVertex("S");
         r1 = test9. new RightVertex("1");
         r2 = test9. new RightVertex("2");
         r3 = test9. new RightVertex("3");
         r4 = test9. new RightVertex("4");
         r5 = test9. new RightVertex("5");

         test9.appendVertex(lS);
         test9.appendVertex(r1);
         test9.appendVertex(r2);
         test9.appendVertex(r3);
         test9.appendVertex(r4);
         test9.appendVertex(r5);

        S1 = new Edge(lS, r1);
        lS.appendEdge(S1);
        r1.appendEdge(S1);
        test9.appendEdge(S1);

        S2 = new Edge(lS, r2);
        lS.appendEdge(S2);
        r2.appendEdge(S2);
        test9.appendEdge(S2);

        S3 = new Edge(lS, r3);
        lS.appendEdge(S3);
        r3.appendEdge(S3);
        test9.appendEdge(S3);

        S4 = new Edge(lS, r4);
        lS.appendEdge(S4);
        r4.appendEdge(S4);
        test9.appendEdge(S4);

        S5 = new Edge(lS, r5);
        lS.appendEdge(S5);
        r5.appendEdge(S5);
        test9.appendEdge(S5);

        Quna.graph = test9;
        Quna.logger.setLogMes(new StringBuilder());
        Quna.algorithm();
        Quna.reserved = test9.getResult();
        Assertions.assertEquals("S", Quna.reserved.get(0).getLeftVertex().getName());
        Assertions.assertEquals("1", Quna.reserved.get(0).getRightVertex().getName());
        // 10
        Graph test10 = new Graph();
        la = test10.new LeftVertex("a");
        lb = test10.new LeftVertex("b");
        Graph.RightVertex rc = test10.new RightVertex("c");
        rd = test10.new RightVertex("d");
        test10.appendVertex(la);
        test10.appendVertex(lb);
        test10.appendVertex(rc);
        test10.appendVertex(rd);
        ead = new Edge(la, rd);
        la.appendEdge(ead);
        rd.appendEdge(ead);
        test10.appendEdge(ead);

        Edge ebc = new Edge(lb, rc);
        lb.appendEdge(ebc);
        rd.appendEdge(ebc);
        test10.appendEdge(ebc);

        Quna.graph = test10;
        Quna.logger.setLogMes(new StringBuilder());
        Quna.algorithm();
        Quna.reserved = test10.getResult();

        Quna.outputToFile("out10.txt");
        Assertions.assertEquals("a", Quna.reserved.get(0).getLeftVertex().getName());
        Assertions.assertEquals("d", Quna.reserved.get(0).getRightVertex().getName());
        Assertions.assertEquals("b", Quna.reserved.get(1).getLeftVertex().getName());
        Assertions.assertEquals("c", Quna.reserved.get(1).getRightVertex().getName());

        // проверка особого случая - пустой граф (ни вершин, ни рёбер)
        Quna.graph = new Graph();
        Quna.logger.setLogMes(new StringBuilder());
        Quna.algorithm();
        Assertions.assertNull(Quna.graph.getResult());


    }
}


