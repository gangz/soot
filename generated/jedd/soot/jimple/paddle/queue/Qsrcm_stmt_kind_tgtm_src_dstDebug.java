package soot.jimple.paddle.queue;

import soot.util.*;
import soot.jimple.paddle.bdddomains.*;
import soot.jimple.paddle.*;
import soot.jimple.toolkits.callgraph.*;
import soot.*;
import soot.util.queue.*;
import jedd.*;
import java.util.*;

public class Qsrcm_stmt_kind_tgtm_src_dstDebug extends Qsrcm_stmt_kind_tgtm_src_dst {
    public Qsrcm_stmt_kind_tgtm_src_dstDebug(String name) { super(name); }
    
    private Qsrcm_stmt_kind_tgtm_src_dstBDD bdd = new Qsrcm_stmt_kind_tgtm_src_dstBDD(name + "bdd");
    
    private Qsrcm_stmt_kind_tgtm_src_dstSet trad = new Qsrcm_stmt_kind_tgtm_src_dstSet(name + "set");
    
    public void add(SootMethod _srcm, Unit _stmt, Kind _kind, SootMethod _tgtm, VarNode _src, VarNode _dst) {
        invalidate();
        bdd.add(_srcm, _stmt, _kind, _tgtm, _src, _dst);
        trad.add(_srcm, _stmt, _kind, _tgtm, _src, _dst);
    }
    
    public void add(final jedd.internal.RelationContainer in) {
        Iterator it =
          new jedd.internal.RelationContainer(new Attribute[] { dst.v(), kind.v(), srcm.v(), src.v(), stmt.v(), tgtm.v() },
                                              new PhysicalDomain[] { V2.v(), KD.v(), MS.v(), V1.v(), ST.v(), MT.v() },
                                              ("in.iterator(new jedd.Attribute[...]) at /home/research/ccl/o" +
                                               "lhota/olhotak/soot-trunk/src/soot/jimple/paddle/queue/Qsrcm_" +
                                               "stmt_kind_tgtm_src_dstDebug.jedd:40,22-24"),
                                              in).iterator(new Attribute[] { srcm.v(), stmt.v(), kind.v(), tgtm.v(), src.v(), dst.v() });
        while (it.hasNext()) {
            Object[] tuple = (Object[]) it.next();
            for (int i = 0; i < 6; i++) {
                add((SootMethod) tuple[0],
                    (Unit) tuple[1],
                    (Kind) tuple[2],
                    (SootMethod) tuple[3],
                    (VarNode) tuple[4],
                    (VarNode) tuple[5]);
            }
        }
    }
    
    public Rsrcm_stmt_kind_tgtm_src_dst reader(String rname) {
        return new Rsrcm_stmt_kind_tgtm_src_dstDebug((Rsrcm_stmt_kind_tgtm_src_dstBDD) bdd.reader(rname),
                                                     (Rsrcm_stmt_kind_tgtm_src_dstSet) trad.reader(rname),
                                                     name + ":" +
                                                     rname);
    }
}