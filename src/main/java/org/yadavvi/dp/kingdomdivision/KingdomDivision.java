package org.yadavvi.dp.kingdomdivision;

/**
 * <p>Explanation by <a href="https://www.hackerrank.com/giaym">@giaym</a> as given
 * <a href="https://www.hackerrank.com/contests/world-codesprint-9/challenges/kingdom-division/forum/comments/243873">here</a>.
 *
 * <p>This is an explanation (not exactly editorial): So, first you have a tree. When you divide the kingdom,
 * it says that if a city is not connected to another, it will cause war.
 *
 * <p>All leaves of the tree can only connect to their parent, so immediately,
 * you can forget about all leaves, and just assume they are connected to their parent.
 *
 * <p>A final leaf could be the root itself, if it only has one child. So if it only has one child,
 * forget about it, and make the new root the child, and remember that the true root is connected to it.
 *
 * <p>Now, you have many nodes that are still not connected so they MUST connect to at least one other node,
 * and some nodes that are already connected, so they dont HAVE TO connect to anything else.
 *
 * <p>Lets imagine the first node (root) is not connected, and has two childs,
 * these childs have one child each, so they are connected already.
 *
 * <ul>
 * <li>0->1</li>
 * <li>0->2</li>
 * </ul>
 *
 * <p>0 is root, besides these there is also nodes 3 and 4, leaves and connected to 1 and 2 so we dont bother with them.
 * Bitmask of connected nodes 011, so node 0 is <em>not connected</em>, node 1 and 2 are <em>connected</em>.
 *
 * <p>Now you need to find all combinations you can make with these.
 * The root could connect to node 1, connect to node 2, connect to both, or connect to neither,
 * this is 4 possibilities, this is because it has two childs, the possibilities are <tt>2^childs</tt>.
 *
 * <p>HOWEVER, the root is not connected, so it HAS to connect to somehting, so "connect to neither" is not available,
 * so possibilities are <tt>2^childs-1</tt>.
 *
 * <p>Nodes 1 and 2 don't have any children to connect to (they are already connected by default),
 * so their "subtrees" only have 1 possibility. So the total setups for that tree is <tt>2^2-1 = 3</tt>,
 * however you have to multiply by 2, to give the one cities to Kid A and the others to Kid B, or the reverse,
 * so 6 is the answer.
 *
 * <p>Now, if your tree is more complex, you still do the same,
 * find the number of possibilities for a subtree (the smallest being next to a leaf, with only one possibility),
 * and work your way up. Once you have defined all the possibilities for the subtrees child to a node,
 * figure out the possibilities for the subtree of the rooting node itself.
 * This is not just a multiplication sadly, if a node has 10 childs.
 *
 * <p>Then you have around <tt>2^10</tt> possibilities of connections to these childs,
 * do you connect to child 1 only? child 2? 3, 4, 5..., or do you connect to 2 childs?
 * 1+2, 1+3, 1+4.. 2+3, 2+4... 9+10?, 3 childs? ... 10 childs? no childs?
 *
 * <p>This is slow and SHOULDNT pass, but it seems to pass the test cases
 * (probably because its medium and not expert), just use a number, have long childsconnected;
 * as a bitmask (like gray codes, but without the gray part).
 * If the bit i is 1, then the child i is connected. So you start with childsconnected = 0; no child is connected.
 * If the parent node is not marked as connected then it wont use this,
 * but if it is already connected (from its own parent), then it can.
 *
 * <p>Then you figure out how many possibilities the subtree has if the root is not connected to any of its childs
 * (multiply the subtrees), store that in your accumulator (addition).
 * Then childsconnected++; makes it 1, or <i>the child 1 is now connected</i>.
 * So again you figure out all the possibilities with only the child 1 connected, add up.
 * Then childsconnected is 2, so the second bit is turned on (child 2 is now connected),
 * and so on until childsconnected = <tt>2^childs-1</tt> which will have all 10 bits turned on.
 *
 * <p>Once you are done, now you have the total number of possibilities for the subtree with root you selected.
 * You save <tt>PC</tt> and <tt>PnotC</tt>, (<i>possibilities if the root is connected</i> and
 * <i>possibilities if it is not connected</i> respectively) so that its parent,
 * when it starts calculating its own possibilities, can just retrieve the correct number
 * instead of recalculating the child subtree. Thats it.
 */
public interface KingdomDivision {

    int numberOfWaysToDivideAKingdom(int n, int[][] roads, int MOD_VALUE);

}
