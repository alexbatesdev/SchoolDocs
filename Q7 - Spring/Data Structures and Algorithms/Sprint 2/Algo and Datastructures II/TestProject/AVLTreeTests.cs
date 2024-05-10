using DataStructures;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AVLTree
{
    [TestClass]
    public class AVLTreeTests
    {
        #region New Tests

        //LeftRotation()
        [TestMethod]
        public void SimpleLeftRotation()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(1);
            tree.Add(2);
            tree.Add(3);
            int[] expected = new int[] { 2, 1, 3 };
            CollectionAssert.AreEqual(expected, tree.ToArray());
        }

        [TestMethod]
        public void LeftRotationWithDangler()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(1);
            tree.Add(0);
            tree.Add(2);
            tree.Add(3);
            tree.Add(4);
            tree.Add(5);

            int[] expected = new int[] { 3, 1, 4, 0, 2, 5 };
            CollectionAssert.AreEqual(expected, tree.ToArray());
        }

        //RightRotation()
        [TestMethod]
        public void SimpleRightRotation()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(3);
            tree.Add(2);
            tree.Add(1);
            int[] expected = new int[] { 2, 1, 3 };
            CollectionAssert.AreEqual(expected, tree.ToArray());
        }

        [TestMethod]
        public void RightRotationWithDangler()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(5);
            tree.Add(6);
            tree.Add(4);
            tree.Add(3);
            tree.Add(2);
            tree.Add(1);

            int[] expected = new int[] { 3, 2, 5, 1, 4, 6 };
            CollectionAssert.AreEqual(expected, tree.ToArray());
        }

        //LeftRightRotation()
        [TestMethod]
        public void SimpleLeftRightRotation()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(3);
            tree.Add(1);
            tree.Add(2);
            int[] expected = new int[] { 2, 1, 3 };
            CollectionAssert.AreEqual(expected, tree.ToArray());
        }

        [TestMethod]
        public void LeftRightRotationOnPopulatedTree() //I really wanted to test the double rotation with a dangler, but I can't figure out a sequence of inputs that results in this. and honestly putting the effort into that won't make me a better developer
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(50);
            tree.Add(25);
            tree.Add(75);
            tree.Add(3);
            tree.Add(4);
            tree.Add(5);
            int[] expected = new int[] { 25, 4, 50, 3, 5, 75 };
            CollectionAssert.AreEqual(expected, tree.ToArray());
        }

        //RightLeftRotation()
        [TestMethod]
        public void SimpleRightLeftRotation()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(1);
            tree.Add(3);
            tree.Add(2);
            int[] expected = new int[] { 2, 1, 3 };
            CollectionAssert.AreEqual(expected, tree.ToArray());
        }

        [TestMethod]
        public void RightLeftRotationOnPopulatedTree() //I really wanted to test the double rotation with a dangler, but I can't figure out a sequence of inputs that results in this. and honestly putting the effort into that won't make me a better developer
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(2);
            tree.Add(1);
            tree.Add(3);
            tree.Add(4);
            tree.Add(6);
            tree.Add(5);
            tree.Add(100);
            tree.Add(50);
            tree.Add(51);
            int[] expected = new int[] { 4, 2, 6, 1, 3, 5, 51, 50, 100 };
            CollectionAssert.AreEqual(expected, tree.ToArray());
        }

        #endregion

        #region Edited Binary Tree Tests
        // Edited due to the structure of the tree being different due to balancing
        // Only changes are the expected outputs, and no these aren't just changed to match the output
        // I remade the tests in https://www.cs.usfca.edu/~galles/visualization/AVLtree.html
        // then ran through the `Root Left Right` or `Left Right Root` pattern on the visualized tree to figure out what the output should be

        //ToArray()
        [TestMethod]
        public void AVL_ToArray()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(5);
            tree.Add(3);
            tree.Add(1);
            tree.Add(8);
            tree.Add(0);
            tree.Add(0);
            tree.Add(8);
            int[] intArray = tree.ToArray();
            int[] expectedIntArray = new int[] { 3, 0, 8, 0, 1, 5, 8 };
            CollectionAssert.AreEqual(expectedIntArray, intArray);
        }

        //PreOrder()
        [TestMethod]
        public void AVL_PreOrder()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(5);
            tree.Add(3);
            tree.Add(1);
            tree.Add(8);
            tree.Add(0);
            tree.Add(0);
            tree.Add(8);
            string actualString = tree.PreOrder();
            string expectedString = "3, 0, 0, 1, 8, 5, 8";
            Assert.AreEqual(expectedString, actualString);
        }

        //PostOrder()
        [TestMethod]
        public void AVL_PostOrder()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(5);
            tree.Add(3);
            tree.Add(1);
            tree.Add(8);
            tree.Add(0);
            tree.Add(0);
            tree.Add(8);
            string actualString = tree.PostOrder();
            string expectedString = "0, 1, 0, 5, 8, 8, 3";
            Assert.AreEqual(expectedString, actualString);
        }
        #endregion

        #region Binary Tree Tests
        //Add
        [TestMethod]
        public void AVL_AddOne()
        {
            //Test if you can add one 
            AVLTree<int> tree = new AVLTree<int>();

            tree.Add(1);

            Assert.AreEqual(1, tree.Count);
            string treeStringRepresentation = tree.InOrder();
            string expectedTreeRepresentation = "1";
            Assert.AreEqual(expectedTreeRepresentation, treeStringRepresentation);
        }

        [TestMethod]
        public void AVL_AddDuplicates()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(1);
            tree.Add(1);
            tree.Add(1);
            Assert.AreEqual(3, tree.Count);
            string treeStringRepresentation = tree.InOrder();
            string expectedTreeRepresentation = "1, 1, 1";
            Assert.AreEqual(expectedTreeRepresentation, treeStringRepresentation);
        }
        //Contains
        //Test on item not in tree
        [TestMethod]
        public void AVL_ContainsOnMissingValue()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(10);
            tree.Add(5);
            tree.Add(15);
            bool result = tree.Contains(9);
            Assert.IsFalse(result);
            Assert.AreEqual(3, tree.Count);
        }
        //Test on duplicate item
        [TestMethod]
        public void AVL_ContainsOnDuplicate()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(10);
            tree.Add(5);
            tree.Add(5);
            bool result = tree.Contains(5);
            Assert.IsTrue(result);
            Assert.AreEqual(3, tree.Count);
        }
        //Test on regular item
        [TestMethod]
        public void AVL_ContainsValue()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(10);
            tree.Add(5);
            tree.Add(15);
            bool result = tree.Contains(15);
            Assert.IsTrue(result);
            Assert.AreEqual(3, tree.Count);
        }
        //Test on Empty tree
        [TestMethod]
        public void AVL_ContainsOnEmptyTree()
        {
            AVLTree<int> tree = new AVLTree<int>();
            bool result = tree.Contains(0);
            Assert.IsFalse(result);
        }

        //Remove
        //Test on empty tree
        [TestMethod]
        public void AVL_RemoveOnEmptyTree()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Remove(0);
            //As long as no exception occurs this test should be good?
            Assert.AreEqual(0, tree.Count);
        }
        //Test on duplicate
        [TestMethod]
        public void AVL_RemoveDuplicate()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(10);
            tree.Add(10);
            tree.Add(15);
            tree.Remove(10);
            Assert.IsTrue(tree.Contains(10));
            string treeStringRepresentation = tree.InOrder();
            string expectedTreeRepresentation = "10, 15";
            Assert.AreEqual(expectedTreeRepresentation, treeStringRepresentation);
            Assert.AreEqual(2, tree.Count);
        }
        //Test Removal of all values
        [TestMethod]
        public void AVL_RemoveAllValues()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(10);
            tree.Add(5);
            tree.Add(15);
            tree.Remove(10);
            tree.Remove(5);
            tree.Remove(15);
            Assert.AreEqual(0, tree.Count);
            string treeStringRepresentation = tree.InOrder();
            string expectedTreeRepresentation = "";
            Assert.AreEqual(expectedTreeRepresentation, treeStringRepresentation);

        }
        //Clear
        //Test on empty tree
        [TestMethod]
        public void AVL_ClearEmptyTree()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Clear();
            //As long as no exception occurs this test should be good?
            Assert.AreEqual(0, tree.Count);
        }
        //Test on regular tree
        [TestMethod]
        public void AVL_ClearPopulatedTree()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(10);
            tree.Add(5);
            tree.Add(15);
            tree.Clear();
            Assert.AreEqual(0, tree.Count);
            string treeStringRepresentation = tree.InOrder();
            string expectedTreeRepresentation = "";
            Assert.AreEqual(expectedTreeRepresentation, treeStringRepresentation);

        }
        //Count
        //Test count on multiple operations
        [TestMethod]
        public void AVL_CountManyOperations()
        {
            AVLTree<int> tree = new AVLTree<int>();
            Assert.AreEqual(0, tree.Count);
            tree.Add(10);
            Assert.AreEqual(1, tree.Count);
            tree.Add(5);
            Assert.AreEqual(2, tree.Count);
            tree.Add(15);
            Assert.AreEqual(3, tree.Count);
            tree.Remove(10);
            Assert.AreEqual(2, tree.Count);
            tree.Remove(5);
            Assert.AreEqual(1, tree.Count);
            tree.Remove(15);
            Assert.AreEqual(0, tree.Count);
        }

        //Height
        //Test on empty tree
        [TestMethod]
        public void AVL_HeightOnEmptyTree()
        {
            AVLTree<int> tree = new AVLTree<int>();
            int expectedHeight = 0;
            int actualHeight = tree.Height();
            Assert.AreEqual(expectedHeight, actualHeight);
        }

        // vvvvv  NOT APPLICABLE BECAUSE THE TREE IS NEVER LOPSIDED  vvvvv
        // I considered just figuring out what the height of these inputs on the AVL tree would be, but then what would this test be testing for? Not Height on a Lopsided Tree, that's for sure

        ////Test on lopsided tree to the left
        //[TestMethod]
        //public void AVL_HeightOnLeftLopsidedTree()
        //{
        //    AVLTree<int> tree = new AVLTree<int>();
        //    tree.Add(10);
        //    tree.Add(5);
        //    tree.Add(15);
        //    tree.Add(9);
        //    tree.Add(8);
        //    tree.Add(6);
        //    tree.Add(2);

        //    int expectedHeight = 5;
        //    int actualHeight = tree.Height();
        //    Assert.AreEqual(expectedHeight, actualHeight);
        //}
        ////Test on lopsided tree to the right
        //[TestMethod]
        //public void AVL_HeightOnRightLopsidedTree()
        //{
        //    AVLTree<int> tree = new AVLTree<int>();
        //    tree.Add(10);
        //    tree.Add(5);
        //    tree.Add(15);
        //    tree.Add(20);
        //    tree.Add(16);
        //    tree.Add(21);
        //    tree.Add(50);

        //    int expectedHeight = 5;
        //    int actualHeight = tree.Height();
        //    Assert.AreEqual(expectedHeight, actualHeight);
        //}
        // ^^^^^  NOT APPLICABLE BECAUSE THE TREE IS NEVER LOPSIDED  ^^^^^

        //ToArray on empty tree
        [TestMethod]
        public void AVL_ToArrayEmptyTree()
        {
            AVLTree<int> tree = new AVLTree<int>();
            int[] intArray = tree.ToArray(); 
            int[] expectedIntArray = new int[] { };
            CollectionAssert.AreEqual(expectedIntArray, intArray);
        }

        //InOrder()
        [TestMethod]
        public void AVL_InOrder()
        {
            AVLTree<int> tree = new AVLTree<int>();
            tree.Add(5);
            tree.Add(3);
            tree.Add(1);
            tree.Add(8);
            tree.Add(0);
            tree.Add(0);
            tree.Add(8);
            string actualString = tree.InOrder();
            string expectedString = "0, 0, 1, 3, 5, 8, 8";
            Assert.AreEqual(expectedString, actualString);
        }
        //InOrder on empty tree
        [TestMethod]
        public void AVL_InOrderOnEmptyTree()
        {
            AVLTree<int> tree = new AVLTree<int>();
            string actualString = tree.InOrder();
            string expectedString = "";
            Assert.AreEqual(expectedString, actualString);
        }

        //PreOrder on empty tree
        [TestMethod]
        public void AVL_PreOrderOnEmptyTree()
        {
            AVLTree<int> tree = new AVLTree<int>();
            string actualString = tree.PreOrder();
            string expectedString = "";
            Assert.AreEqual(expectedString, actualString);
        }

        //InOrder on empty tree
        [TestMethod]
        public void AVL_PostOrderOnEmptyTree()
        {
            AVLTree<int> tree = new AVLTree<int>();
            string actualString = tree.PostOrder();
            string expectedString = "";
            Assert.AreEqual(expectedString, actualString);
        }
        #endregion
    }
}
