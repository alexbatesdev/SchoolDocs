using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataStructures
{
    public class DoubleLinkedList<T> where T :IComparable<T>
    {
        public DoubleLinkedList() {
            First = null;
            Last = null;
            Count = 0; //Redundant I think
        }

        public DoubleLinkedList(T[] array)
        {
            for (int i = 0; i < array.Length; i++)
            {
                Add(array[i]);
            }
        }

        public DoubleLinkedListNode<T> First;
        public DoubleLinkedListNode<T> Last;
        public int Count { get; private set; } = 0;

        public void Add(T value)
        {
            DoubleLinkedListNode<T> node = new DoubleLinkedListNode<T>(value); ;
            if (First == null)
            {
                First = node;
                Last = node;
            } else
            {
                Last.nextNode = node;
                node.prevNode = Last;
                Last = node;
            }
            Count++;
        }

        public void Insert(T value, int index)
        {
            if (index < 0)
            {
                throw new ArgumentOutOfRangeException("index");
            }
            if (index >= Count)
            {
                throw new ArgumentOutOfRangeException("index");
            }

            DoubleLinkedListNode<T> targetNode = First;
            int iter = 0;
            bool iterating = true;

            while(targetNode != null && iterating)
            {
                if (index == 0)
                {
                    DoubleLinkedListNode<T> tempNode = First;
                    First = new DoubleLinkedListNode<T>(value, null, tempNode);
                    tempNode.prevNode = First;
                    iterating = false;
                    continue;
                }

                if (iter == index)
                {
                    DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(value, targetNode.prevNode, targetNode);
                    targetNode.prevNode.nextNode = newNode;
                    targetNode.prevNode = newNode;
                    iterating = false;
                }

                targetNode = targetNode.nextNode;
                iter++;
            }
            Count++;
        }

        public T Get(int index)
        {
            if (index < 0)
            {
                throw new ArgumentOutOfRangeException("index");
            }
            else if (index >= Count)
            {
                throw new ArgumentOutOfRangeException("index");
            }

            if (index == 0)
            {
                return First.value;
            }
            else if (index == Count)
            {
                return Last.value;
            }
            else
            {
                DoubleLinkedListNode<T> targetNode = First;
                int iter = 0;

                while (targetNode != null)
                {
                    if (iter == index)
                    {
                        return targetNode.value;
                    }
                    targetNode = targetNode.nextNode;
                    iter++;
                }
                // I don't think this should ever get hit
                Console.WriteLine("Wow, you hit something weird");
                return default(T);
            }
        }

        public T RemoveFirst()
        {
            if (Count == 0)
            {
                throw new InvalidOperationException();
            }
            T output = First.value;
            if (Count != 1)
            {
                First = First.nextNode;
                First.prevNode = null;
            } else
            {
                First = null;
            }
            Count--;
            return output;
        }

        public T RemoveLast()
        {
            if (Count == 0)
            {
                throw new InvalidOperationException();
            }
            T output = Last.value;

            Last = Last.prevNode;
            Last.nextNode = null;
            Count--;
            return output;
        }

        public T RemoveAt(int index)
        {
            if (index < 0)
            {
                throw new ArgumentOutOfRangeException("index");
            }
            else if (index >= Count)
            {
                throw new ArgumentOutOfRangeException("index");
            }

            if (index == 0)
            {
                return RemoveFirst();
            }
            if (index == Count - 1)
            {
                return RemoveLast();
            }

            DoubleLinkedListNode<T> targetNode = First;
            int iter = 0;
            while (targetNode != null)
            {
                if (iter == index)
                {
                    T output = targetNode.value;
                    DoubleLinkedListNode<T> targetPrevNode = targetNode.prevNode;
                    DoubleLinkedListNode<T> targetNextNode = targetNode.nextNode;

                    targetPrevNode.nextNode = targetNextNode;
                    targetNextNode.prevNode = targetPrevNode;

                    Count--;
                    return output;
                }

                targetNode = targetNode.nextNode;
                iter++;
            }
            return default(T);
        }

        public void Clear()
        {
            First = null;
            Last = null;
            Count = 0;
        }

        public int Search(T value)
        {
            if(First == null)
            {
                return -1;
            }

            DoubleLinkedListNode<T> targetNode = First;
            int iter = 0;

            while (targetNode != null)
            {
                if (targetNode.value.CompareTo(value) == 0)
                {
                    return iter;
                } else
                {
                    targetNode = targetNode.nextNode;
                    iter++;
                }
            }
            return -1;
        }

        public override string ToString()
        {
            DoubleLinkedListNode<T> targetNode = First;
            string output = "";

            while (targetNode != null)
            {
                output += targetNode.value;
                if (targetNode.nextNode != null)
                {
                    output += ", ";
                }
                targetNode = targetNode.nextNode;
            }
            output += "";
            return output;
        }
    }

    public class DoubleLinkedListNode<T> where T :IComparable<T>
    {
        public T value { get; set; }
        public DoubleLinkedListNode<T> nextNode { get; set; }
        public DoubleLinkedListNode<T> prevNode { get; set; }

        public DoubleLinkedListNode(T value)
        {
            this.value = value;
            this.nextNode = null;
            this.prevNode = null;
        }

        public DoubleLinkedListNode(T value, DoubleLinkedListNode<T> prevNode, DoubleLinkedListNode<T> nextNode)
        {
            this.value = value;
            this.nextNode = nextNode;
            this.prevNode = prevNode;
        }
    }
}
