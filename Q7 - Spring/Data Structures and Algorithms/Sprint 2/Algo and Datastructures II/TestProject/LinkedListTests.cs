using DataStructures;
using NuGet.Frameworks;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LinkedLists
{
    [TestClass]
    public class LinkedListTests
    {
        #region SingleLinkedListTests
        //1
        [TestMethod]
        public void SLL_EmptyCount()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>();
            int expectedCount = 0;
            int actualCount = sll.Count;

            Assert.AreEqual(expectedCount, actualCount);
        }
        //2
        [TestMethod]
        public void SLL_ToStringEmptyList()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>();
            string expectedList = "";
            Assert.AreEqual(expectedList, sll.ToString());
        }
        //3
        [TestMethod]
        public void SLL_AddOneItem()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>();
            sll.Add(1);
            int expectedCount = 1;
            int actualCount = sll.Count;
            Assert.AreEqual(expectedCount, actualCount);
            string expectedList = "1";
            Assert.AreEqual(expectedList, sll.ToString());
        }
        //4
        [TestMethod]
        public void SLL_AddTwoItems()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>();
            sll.Add(1);
            sll.Add(2);
            int expectedCount = 2;
            int actualCount = sll.Count;
            Assert.AreEqual(expectedCount, actualCount);
            string expectedList = "1, 2";
            Assert.AreEqual(expectedList, sll.ToString());
        }
        //5
        [TestMethod]
        public void SLL_InsertOnEmptyList()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>();
            Assert.ThrowsException<ArgumentOutOfRangeException>(() =>
            {
                sll.Insert(3, 0);
            });
        }
        //6
        [TestMethod]
        public void SLL_InsertOneItem()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>(new int[] {5,4,3,2,1});
            sll.Insert(100, 1);
            int expectedCount = 6;
            int actualCount = sll.Count;
            Assert.AreEqual(expectedCount, actualCount);
            string expectedList = "5, 100, 4, 3, 2, 1";
            Assert.AreEqual(expectedList, sll.ToString());
        }
        //7
        [TestMethod]
        public void SLL_InsertAtEnd()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>(new int[] { 5,3,1,8,0,8 });
            sll.Insert(0, sll.Count - 1);
            string expectedList = "5, 3, 1, 8, 0, 0, 8";
            int expectedCount = 7;
            int actualCount = sll.Count;
            Assert.AreEqual(expectedCount, actualCount);
            Assert.AreEqual(expectedList, sll.ToString());
        }
        //8
        [TestMethod]
        public void SLL_RemoveFirstEmptyList()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>();
            Assert.ThrowsException<InvalidOperationException>(() => sll.RemoveFirst());
            Assert.AreEqual(0, sll.Count);
        }
        //9
        [TestMethod]
        public void SLL_RemoveFirstPopulatedList()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>(new int[] { 6, 9, 8, 7 });
            sll.RemoveFirst();
            string expectedList = "9, 8, 7";
            int expectedCount = 3;
            int actualCount = sll.Count;
            Assert.AreEqual(expectedCount, actualCount);
            Assert.AreEqual(expectedList, sll.ToString());
        }
        //10
        [TestMethod]
        public void SLL_RemoveLastEmptyList()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>();
            Assert.ThrowsException<InvalidOperationException>( () => sll.RemoveLast());
        }
        //11
        [TestMethod]
        public void SLL_RemoveLastPopulatedList()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>(new int[] {1, 9, 8, 4});
            sll.RemoveLast();
            Assert.AreEqual(3, sll.Count);
            string expectedList = "1, 9, 8";
            Assert.AreEqual(expectedList, sll.ToString());
        }
        //12
        [TestMethod]
        public void SLL_RemoveAtEmptyList()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>();
            Assert.ThrowsException<ArgumentOutOfRangeException>(() => sll.RemoveAt(0));

        }
        //13
        [TestMethod]
        public void SLL_RemoveAtPopulatedList()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>(new int[] {5,31,12,1,100,1,6});
            sll.RemoveAt(4);
            string expectedList = "5, 31, 12, 1, 1, 6";
            Assert.AreEqual(expectedList, sll.ToString());
            Assert.AreEqual(6, sll.Count);
        }
        //14
        [TestMethod]
        public void SLL_RemoveAtFirst()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>(new int[] { 5, 3, 1, 8, 0, 8 });
            sll.RemoveAt(0);
            string expectedList = "3, 1, 8, 0, 8";
            Assert.AreEqual(expectedList, sll.ToString());
            Assert.AreEqual(5, sll.Count);
        }
        //15
        [TestMethod]
        public void SLL_RemoveAtLast()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>(new int[] { 5, 3, 1, 8, 0, 8 });
            sll.RemoveAt(sll.Count - 1);
            string expectedList = "5, 3, 1, 8, 0";
            Assert.AreEqual(expectedList, sll.ToString());
            Assert.AreEqual(5, sll.Count);
        }
        //16
        [TestMethod]
        public void SLL_ClearEmptyList()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>();
            sll.Clear();
            string expectedList = "";
            Assert.AreEqual(expectedList, sll.ToString());
            Assert.AreEqual(0, sll.Count);
        }
        //17
        [TestMethod]
        public void SLL_ClearPopulatedList()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>(new int[] { 1, 3, 1, 2 });
            sll.Clear();
            string expectedList = "";
            Assert.AreEqual(expectedList, sll.ToString());
            Assert.AreEqual(0, sll.Count);
        }
        //18
        [TestMethod]
        public void SLL_GetFromEmptyList()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>();
            Assert.ThrowsException<ArgumentOutOfRangeException>(() => sll.Get(0));
            

        }
        //19
        [TestMethod]
        public void SLL_GetFromPopulatedList()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>(new int[] { 2, 0, 0, 2 });
            int expectedValue = 0;
            int actualValue = sll.Get(1);
            Assert.AreEqual(expectedValue, actualValue);
        }
        //20
        [TestMethod]
        public void SLL_SearchFromEmptyList()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>();
            int expectedValue = -1;
            int actualValue = sll.Search(0);
            Assert.AreEqual(expectedValue, actualValue);
        }
        //21
        [TestMethod]
        public void SLL_SearchFromPopulatedListWithRepeats()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>(new int[] { 2, 0, 0, 2 });
            int expectedValue = 0;
            int actualValue = sll.Search(2);
            Assert.AreEqual(expectedValue, actualValue);
        }
        //22
        [TestMethod]
        public void SLL_SearchForItemNotFromPopulatedList()
        {
            SingleLinkedList<int> sll = new SingleLinkedList<int>(new int[] { 2, 0, 0, 2 });
            int expectedValue = -1;
            int actualValue = sll.Search(3);
            Assert.AreEqual(expectedValue, actualValue);

        }

        //Print on empty list

        //Most actions on empty list

        //Get first, Get Last
        //Insert first, insert last
        //RemoveAt first and last
        //Most actions on first and last

        //Test that methods properly update the count

        #endregion

        #region DoubleLinkedListTests
        //1
        [TestMethod]
        public void DLL_EmptyCount()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>();
            int expectedCount = 0;
            Assert.AreEqual(expectedCount, list.Count);
        }
        //2
        [TestMethod]
        public void DLL_EmptyToString()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>();
            string expectedList = "";
            Assert.AreEqual(expectedList, list.ToString());
        }
        //3
        [TestMethod]
        public void DLL_AddValues()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>();
            int expectedCount = 4;
            string expectedList = "0, 1, 4, 15";
            list.Add(0);
            list.Add(1);
            list.Add(4);
            list.Add(15);
            Assert.AreEqual(expectedCount, list.Count);
            Assert.AreEqual(expectedList, list.ToString());
        }
        //4
        [TestMethod]
        public void DLL_AddandRemove()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>();
            int expectedCount = 0;
            string expectedList = "";
            list.Add(1);
            list.Add(2);
            list.Add(3);
            list.RemoveFirst();
            list.RemoveFirst();
            list.RemoveFirst();
            Assert.AreEqual(expectedCount, list.Count);
            Assert.AreEqual(expectedList, list.ToString());
        }
        //5
        [TestMethod]
        public void DLL_InsertBeginning()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>(new int[] { 4, 6, 3, 7, 9 });
            int expectedCount = 6;
            string expectedList = "100, 4, 6, 3, 7, 9";
            list.Insert(100, 0);
            Assert.AreEqual(expectedCount, list.Count);
            Assert.AreEqual(expectedList, list.ToString());

        }
        //6
        [TestMethod]
        public void DLL_InsertEnd()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>(new int[] { 4, 6, 3, 7, 9 });
            Assert.ThrowsException<ArgumentOutOfRangeException>(() => list.Insert(100, 5));
        }
        //7
        [TestMethod]
        public void DLL_GetFirst()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>(new int[] { 4, 6, 3, 7, 9 });
            Assert.AreEqual(4, list.Get(0));
        }
        //8
        [TestMethod]
        public void DLL_GetLast()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>(new int[] { 4, 6, 3, 7, 9 });
            Assert.AreEqual(9, list.Get(list.Count - 1));
        }
        //9
        [TestMethod]
        public void DLL_GetOutOfRange()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>(new int[] { 4, 6, 3, 7, 9 });
            Assert.ThrowsException<ArgumentOutOfRangeException> (() => list.Get(20));
        }
        //10
        [TestMethod]
        public void DLL_GetNegative()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>(new int[] { 4, 6, 3, 7, 9 });
            Assert.ThrowsException<ArgumentOutOfRangeException>(() => list.Get(-1));
        }
        //11
        [TestMethod]
        public void DLL_RemoveFirstEmptyList()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>();
            Assert.ThrowsException<InvalidOperationException> (() => list.RemoveFirst());
        }
        //12
        [TestMethod]
        public void DLL_RemoveFirstPopulatedList()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>(new int[] { 4, 6, 3, 7, 9 });
            int expectedCount = 4;
            string expectedList = "6, 3, 7, 9";
            list.RemoveFirst();
            Assert.AreEqual(expectedCount, list.Count);
            Assert.AreEqual(expectedList, list.ToString());
        }
        //13
        [TestMethod]
        public void DLL_RemoveLastEmptyList()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>();
            Assert.ThrowsException<InvalidOperationException>(() => list.RemoveLast());
        }
        //14
        [TestMethod]
        public void DLL_RemoveAtEmptyList()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>();
            Assert.ThrowsException<ArgumentOutOfRangeException>(() => list.RemoveAt(0));
        }
        //15
        [TestMethod]
        public void DLL_RemoveAtPopulatedList()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>(new int[] { 5, 7, 3, 4 });
            int expectedCount = 3;
            string expectedList = "5, 7, 4";
            list.RemoveAt(2);
            Assert.AreEqual(expectedCount, list.Count);
            Assert.AreEqual(expectedList, list.ToString());
        }
        //16
        [TestMethod]
        public void DLL_ClearPopulatedList()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>(new int[] { 4, 6, 3, 7, 9 });
            int expectedCount = 0;
            string expectedList = "";
            list.Clear();
            Assert.AreEqual(expectedCount, list.Count);
            Assert.AreEqual(expectedList, list.ToString());
        }
        //17
        [TestMethod]
        public void DLL_ClearEmptyList()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>();
            int expectedCount = 0;
            string expectedList = "";
            list.Clear();
            Assert.AreEqual(expectedCount, list.Count);
            Assert.AreEqual(expectedList, list.ToString());
        }
        //18
        [TestMethod]
        public void DLL_ConstructorInitialization()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>(new int[] { 4, 6, 3, 7, 9 });
            int expectedCount = 5;
            string expectedList = "4, 6, 3, 7, 9";

            Assert.AreEqual(expectedCount, list.Count);
            Assert.AreEqual(expectedList, list.ToString());
        }
        //19
        [TestMethod]
        public void DLL_CountAfterOperations()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>();
            int expectedCount = 2;
            string expectedList = "1, 2";
            list.Add(4);
            list.Add(1);
            list.Add(2);
            list.Insert(1, 2);
            list.RemoveFirst();
            list.Add(3);
            list.RemoveLast();
            list.RemoveAt(1);
            Assert.AreEqual(expectedCount, list.Count);
            Assert.AreEqual(expectedList, list.ToString());
        }
        //20
        [TestMethod]
        public void DLL_ToStringOnPopulatedList()
        {
            DoubleLinkedList<int> list = new DoubleLinkedList<int>(new int[] {5, 3, 1, 8, 0, 0, 8});
            string expectedList = "5, 3, 1, 8, 0, 0, 8";
            Assert.AreEqual(expectedList, list.ToString()); 
        }

        #endregion
    }
}
