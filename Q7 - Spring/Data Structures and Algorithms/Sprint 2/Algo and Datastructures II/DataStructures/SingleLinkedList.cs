using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Threading.Tasks;

namespace DataStructures
{
    public class SingleLinkedList<T> where T : IComparable<T>
    {
        public SingleLinkedList() {
            First = null;
            Last = null;
            Count = 0;
        }

        public SingleLinkedList(T[] array)
        {
            for (int i = 0; i < array.Length; i++)
            {
                Add(array[i]);
            }
        }

        public SingleLinkedListNode<T> First;
        public SingleLinkedListNode<T> Last;
        public int Count { get; private set; } = 0;

        public void Add(T value)
        {
            SingleLinkedListNode<T> node = new SingleLinkedListNode<T>(value);
           if (First == null)
            {
                First = node;
                Last = node;
            } else
            {
                Last.nextNode = node;
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

            SingleLinkedListNode<T> targetNode = First;
            SingleLinkedListNode<T> tempNode = null;
            int iter = 0;
            bool iterating = true;

            while (targetNode != null && iterating)
            {
                if (index == 0)
                {
                    tempNode = First;
                    First = new SingleLinkedListNode<T>(value, tempNode);
                    iterating = false;
                    continue;
                }

                if (iter == index)
                {
                    tempNode.nextNode = new SingleLinkedListNode<T>(value, targetNode);
                }

                // I'm trying to make temp node iterate one step behind the target node
                tempNode = targetNode;
                targetNode = targetNode.nextNode;
                iter++;
            }
            Count++;
        }

        public T Get(int index)
        {
            if (index >= Count)
                throw new ArgumentOutOfRangeException("index");


            if (index <= 0)
            {
                return First.value;
            } else if (index == Count)
            {
                return Last.value;
            } else
            {
                SingleLinkedListNode<T> targetNode = First;
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

            First = First.nextNode;
            Count--;
            return output;
        }

        public T RemoveLast()
        {
            if (Count == 0)
            {
                throw new InvalidOperationException();
            }
            SingleLinkedListNode<T> targetNode = First;
            T output = Last.value;
            while (targetNode != null)
            {
                if (targetNode.nextNode == Last)
                {
                    targetNode.nextNode = null;
                    Count--;
                } 
                targetNode = targetNode.nextNode;
            }
            return output;
        }

        public T RemoveAt(int index)
        {
            if (index < 0)
            {
                throw new ArgumentOutOfRangeException("index");
            } else if (index >= Count)
            {
                throw new ArgumentOutOfRangeException("index");
            }

            if (index == 0)
            {
                return RemoveFirst();
            } if (index == Count - 1)
            {
                return RemoveLast();
            }

            SingleLinkedListNode<T> trailingTargetNode = First;
            SingleLinkedListNode<T> leadingTargetNode = First.nextNode;
            T output;
            int iter = 1;
            while (leadingTargetNode != null)
            {
                if (iter == index)
                {
                    output = leadingTargetNode.value;
                    trailingTargetNode.nextNode = leadingTargetNode.nextNode;
                    Count--;
                    return output;
                }

                trailingTargetNode = trailingTargetNode.nextNode;
                leadingTargetNode = leadingTargetNode.nextNode;
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
            if (First == null)
            {
                return -1;
            }

            SingleLinkedListNode<T> targetNode = First;
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

        override public string ToString()
        {
            string output = "";

            SingleLinkedListNode<T> targetNode = First;

            while (targetNode != null)
            {
                output += targetNode.value + ", ";
                targetNode = targetNode.nextNode;
            }
            if (Count != 0)
            {
                // This line gets rid of the extra comma added 3 lines prior
                output = output.Substring(0, output.Length - 2);
            }
            
            return output;
        }

        public string Print()
        {

            Type type = typeof(T);
            string output = "Type: " + type.Name + "\n" + "Count: " + Count + "\n" + "List: [";

            SingleLinkedListNode<T> targetNode = First;

            while (targetNode != null)
            {
                output += targetNode.value + ", ";
                targetNode = targetNode.nextNode;
            }
            if (Count != 0)
            {
                // This line gets rid of the extra comma added 3 lines prior
                output = output.Substring(0, output.Length - 2);
            }

            return output + "]";
        }
    }

    public class SingleLinkedListNode<T> where T : IComparable<T>
    {
        public T value { get; set; }
        public SingleLinkedListNode<T> nextNode { get; set; }

        public SingleLinkedListNode(T value)
        {
            this.value = value;
            this.nextNode = null;
        }

        public SingleLinkedListNode(T value, SingleLinkedListNode<T> nextNode)
        {
            this.value = value;
            this.nextNode = nextNode;
        }

    }
}
