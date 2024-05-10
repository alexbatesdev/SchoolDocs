using DataStructures;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;

namespace BinaryTree
{

    [TestClass]
    public class BinaryTreeTests
    {
        //Add
        [TestMethod]
        public void BST_AddOne()
        {
            //Test if you can add one 
            BinaryTree<int> tree = new BinaryTree<int>();

            tree.Add(1);

            Assert.AreEqual(1, tree.Count);
            string treeStringRepresentation = tree.InOrder();
            string expectedTreeRepresentation = "1";
            Assert.AreEqual(expectedTreeRepresentation, treeStringRepresentation);
        }

        [TestMethod]
        public void BST_AddDuplicates()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
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
        public void BST_ContainsOnMissingValue()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
            tree.Add(10);
            tree.Add(5);
            tree.Add(15);
            bool result = tree.Contains(9);
            Assert.IsFalse(result);
            Assert.AreEqual(3, tree.Count);
        }
        //Test on duplicate item
        [TestMethod]
        public void BST_ContainsOnDuplicate()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
            tree.Add(10);
            tree.Add(5);
            tree.Add(5);
            bool result = tree.Contains(5);
            Assert.IsTrue(result);
            Assert.AreEqual(3, tree.Count);
        }
        //Test on regular item
        [TestMethod]
        public void BST_ContainsValue()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
            tree.Add(10);
            tree.Add(5);
            tree.Add(15);
            bool result = tree.Contains(15);
            Assert.IsTrue(result);
            Assert.AreEqual(3, tree.Count);
        }
        //Test on Empty tree
        [TestMethod]
        public void BST_ContainsOnEmptyTree()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
            bool result = tree.Contains(0);
            Assert.IsFalse(result);
        }

        //Remove
        //Test on empty tree
        [TestMethod]
        public void BST_RemoveOnEmptyTree()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
            tree.Remove(0);
            //As long as no exception occurs this test should be good?
            Assert.AreEqual(0, tree.Count);
        }
        //Test on duplicate
        [TestMethod]
        public void BST_RemoveDuplicate()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
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
        public void BST_RemoveAllValues()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
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
        public void BST_ClearEmptyTree()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
            tree.Clear();
            //As long as no exception occurs this test should be good?
            Assert.AreEqual(0, tree.Count);
        }
        //Test on regular tree
        [TestMethod]
        public void BST_ClearPopulatedTree()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
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
        public void BST_CountManyOperations()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
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
        public void BST_HeightOnEmptyTree()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
            int expectedHeight = 0;
            int actualHeight = tree.Height();
            Assert.AreEqual(expectedHeight, actualHeight);
        }
        //Test on lopsided tree to the left
        [TestMethod]
        public void BST_HeightOnLeftLopsidedTree()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
            tree.Add(10);
            tree.Add(5);
            tree.Add(15);
            tree.Add(9);
            tree.Add(8);
            tree.Add(6);
            tree.Add(2);

            int expectedHeight = 5;
            int actualHeight = tree.Height();
            Assert.AreEqual(expectedHeight, actualHeight);
        }
        //Test on lopsided tree to the right
        [TestMethod]
        public void BST_HeightOnRightLopsidedTree()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
            tree.Add(10);
            tree.Add(5);
            tree.Add(15);
            tree.Add(20);
            tree.Add(16);
            tree.Add(21);
            tree.Add(50);

            int expectedHeight = 5;
            int actualHeight = tree.Height();
            Assert.AreEqual(expectedHeight, actualHeight);
        }
        //ToArray()
        [TestMethod]
        public void BST_ToArray()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
            tree.Add(5);
            tree.Add(3);
            tree.Add(1);
            tree.Add(8);
            tree.Add(0);
            tree.Add(0);
            tree.Add(8);
            int[] intArray = (int[])tree.ToArray(); //<--------------------------------------------- ASK ABOUT ME <--------------------------------------------------
            int[] expectedIntArray = new int[] { 0, 0, 1, 3, 5, 8, 8 };
            CollectionAssert.AreEqual(expectedIntArray, intArray);
        }
        //ToArray on empty tree
        [TestMethod]
        public void BST_ToArrayEmptyTree()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
            int[] intArray = (int[])tree.ToArray(); //<---------- ASK ABOUT ME <-----------------
            int[] expectedIntArray = new int[] { };
            CollectionAssert.AreEqual(expectedIntArray, intArray);
        }

        //InOrder()
        [TestMethod]
        public void BST_InOrder()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
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
        public void BST_InOrderOnEmptyTree()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
            string actualString = tree.InOrder();
            string expectedString = "";
            Assert.AreEqual(expectedString, actualString);
        }

        //PreOrder()
        [TestMethod]
        public void BST_PreOrder()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
            tree.Add(5);
            tree.Add(3);
            tree.Add(1);
            tree.Add(8);
            tree.Add(0);
            tree.Add(0);
            tree.Add(8);
            string actualString = tree.PreOrder();
            string expectedString = "5, 3, 1, 0, 0, 8, 8";
            Assert.AreEqual(expectedString, actualString);
        }
        //PreOrder on empty tree
        [TestMethod]
        public void BST_PreOrderOnEmptyTree()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
            string actualString = tree.PreOrder();
            string expectedString = "";
            Assert.AreEqual(expectedString, actualString);
        }

        //PostOrder()
        [TestMethod]
        public void BST_PostOrder()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
            tree.Add(5);
            tree.Add(3);
            tree.Add(1);
            tree.Add(8);
            tree.Add(0);
            tree.Add(0);
            tree.Add(8);
            string actualString = tree.PostOrder();
            string expectedString = "0, 0, 1, 3, 8, 8, 5";
            Assert.AreEqual(expectedString, actualString);
        }
        //InOrder on empty tree
        [TestMethod]
        public void BST_PostOrderOnEmptyTree()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
            string actualString = tree.PostOrder();
            string expectedString = "";
            Assert.AreEqual(expectedString, actualString);
        }
    }
}
