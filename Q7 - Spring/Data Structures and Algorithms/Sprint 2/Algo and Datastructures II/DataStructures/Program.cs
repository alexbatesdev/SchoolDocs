using System.Text;

namespace DataStructures
{
    internal class Program
    {
        static void Main(string[] args)
        {
            //BinaryTree<int> tree = new BinaryTree<int>();
            //tree.Add(10);
            //tree.Add(5);
            //tree.Add(15);
            //tree.Add(4);
            ////Console.WriteLine(tree.Count);
            //tree.Add(6);
            //tree.Add(25);
            //tree.Add(16);
            //tree.Add(17);
            //tree.Add(18);
            //tree.Add(19);
            ////tree.Clear();
            ////Console.WriteLine(tree.Count);
            //int[] array = (int[])tree.ToArray();
            //tree.Remove(5);
            ////Console.WriteLine(tree.Count);
            //tree.Remove(25);
            ////Console.WriteLine(tree.Count);
            ////tree.Remove(16);
            //tree.Remove(17);
            ////Console.WriteLine(tree.Count);
            ////tree.Remove(18);


            ////Console.WriteLine(tree.PostOrder());
            //Console.WriteLine(tree.InOrder());

            ////Console.WriteLine(tree.PreOrder());

            //for (int i = 0; i < array.Length; i++)
            //{
            //    Console.Write(array[i] + " ");
            //    Console.WriteLine(tree.Contains(array[i]));
            //}

            //Console.WriteLine(tree.Height());

            //BinaryTree<int> tree = new BinaryTree<int>();
            //tree.Add(10);
            //tree.Add(10);
            //tree.Add(15);
            //tree.Remove(10);

            //string treeString = tree.InOrder();
            //Console.WriteLine(treeString);

            // -------------------------------------- Tests --------------------------------------------
            ////LR, RR
            //AVLTree<int> tree = new AVLTree<int>();
            //Console.Write("Input Order: ");
            //Console.WriteLine("3, 1, 2");
            //tree.Add(3);
            //PrintMyTree(tree);
            //tree.Add(1);
            //PrintMyTree(tree);
            //tree.Add(2);
            //PrintMyTree(tree);
            ////LR
            //tree = new AVLTree<int>();
            //Console.Write("Input Order: ");
            //Console.WriteLine("1, 2, 3");
            //tree.Add(1);
            //PrintMyTree(tree);
            //tree.Add(2);
            //PrintMyTree(tree);
            //tree.Add(3);
            //PrintMyTree(tree);
            ////RR
            //tree = new AVLTree<int>();
            //Console.Write("Input Order: ");
            //Console.WriteLine("3, 2, 1");
            //tree.Add(3);
            //PrintMyTree(tree);
            //tree.Add(2);
            //PrintMyTree(tree);
            //tree.Add(1);
            //PrintMyTree(tree);
            ////RR, LR
            //tree = new AVLTree<int>();
            //Console.Write("Input Order: ");
            //Console.WriteLine("1, 3, 2");
            //tree.Add(1);
            //PrintMyTree(tree);
            //tree.Add(3);
            //PrintMyTree(tree);
            //tree.Add(2);
            //PrintMyTree(tree);
            //AVLTree<int> tree = new AVLTree<int>();
            //Console.Write("Input Order: ");
            //Console.WriteLine("3, 2, 5, 4, 6, 7, 70, 0, 16, 15, 30");
            //tree.Add(3);
            //PrintMyTree(tree);
            //tree.Add(2);
            //PrintMyTree(tree);
            //tree.Add(5);
            //PrintMyTree(tree);
            //tree.Add(4);
            //PrintMyTree(tree);
            //tree.Add(6);
            //PrintMyTree(tree);
            ////for (int i = 0; i < tree.ToArray().Length; i++)
            ////{
            ////    Console.Write(((int[])tree.ToArray())[i] + ", ");
            ////}
            ////Console.WriteLine();
            //tree.Remove(2);
            //PrintMyTree(tree);
            //tree.Add(7);
            //PrintMyTree(tree);
            //tree.Add(70); //Should rotate left at 7
            //PrintMyTree(tree);
            //tree.Add(0);
            //PrintMyTree(tree);
            //tree.Add(16);
            //PrintMyTree(tree);
            //tree.Add(15);
            //PrintMyTree(tree);
            //tree.Add(30);
            //PrintMyTree(tree);
            //for (int i = 0; i < tree.ToArray().Length; i++)
            //{
            //    Console.Write(((int[])tree.ToArray())[i] + ", ");
            //}
            //Console.WriteLine();

            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(1);
            tree.Add(0);
            tree.Add(2);
            tree.Add(3);
            tree.Add(4);
            tree.Add(5);
            PrintMyTree(tree);


            //Console.WriteLine("5, 3, 1, 8, 0, 0, 8");
            //tree.Add(5);
            //PrintMyTree(tree);
            //tree.Add(3);
            //PrintMyTree(tree);
            //tree.Add(1);
            //PrintMyTree(tree);
            //tree.Add(8);
            //PrintMyTree(tree);
            //tree.Add(0);
            //PrintMyTree(tree);
            //tree.Add(0);
            //PrintMyTree(tree);
            //tree.Add(8);
            //PrintMyTree(tree);

            //Console.WriteLine("In Order");
            //Console.WriteLine(tree.InOrder());
            //Console.WriteLine("Pre Order");
            //Console.WriteLine(tree.PreOrder());
            //Console.WriteLine("Post Order");
            //Console.WriteLine(tree.PostOrder());
            //int[] intArray = (int[])tree.ToArray();
            //BinaryTree<int> treee = new BinaryTree<int>();
            //treee.Add(5);
            //treee.Add(3);
            //treee.Add(1);
            //treee.Add(8);
            //treee.Add(0);
            //treee.Add(0);
            //treee.Add(8);
            //Console.WriteLine("Input Order");
            //Console.WriteLine("5, 3, 1, 8, 0, 0, 8");


            //PrintMyTree(tree);
            //Console.WriteLine("In Order");
            //Console.WriteLine(treee.InOrder());
            //Console.WriteLine("Pre Order");
            //Console.WriteLine(treee.PreOrder());
            //Console.WriteLine("Post Order");
            //Console.WriteLine(treee.PostOrder());
            //int[] expectedIntArray = new int[] { 0, 0, 1, 3, 5, 8, 8 };



        }

        protected static void PrintMyTree(AVLTree<int> tree)
        {
            if (tree.Root == null)
            {
                Console.WriteLine("Tree is empty");
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.Append(tree.Root.Value + " (root)");
            string pointerRight = "└──";
            string pointerLeft = (tree.Root.Right != null) ? "├──" : "└──";

            TraverseNodes(sb, "", pointerLeft, tree.Root.Left, tree.Root.Right != null);
            TraverseNodes(sb, "", pointerRight, tree.Root.Right, false);

            Console.WriteLine(sb.ToString());
        }

        public static void TraverseNodes(StringBuilder sb, string padding, string pointer, AVLTreeNode<int> node, bool hasRightSibling)
        {
            if (node != null)
            {
                sb.Append("\n");
                sb.Append(padding);
                sb.Append(pointer);
                sb.Append(node.Value);

                StringBuilder paddingBuilder = new StringBuilder(padding);
                if (hasRightSibling)
                {
                    paddingBuilder.Append("│  ");
                }
                else
                {
                    paddingBuilder.Append("   ");
                }

                String paddingForBoth = paddingBuilder.ToString();
                String pointerRight = "└──";
                String pointerLeft = (node.Right != null) ? "├──" : "└──";

                TraverseNodes(sb, paddingForBoth, pointerLeft, node.Left, node.Right != null);
                TraverseNodes(sb, paddingForBoth, pointerRight, node.Right, false);
            }
        }
    }
}