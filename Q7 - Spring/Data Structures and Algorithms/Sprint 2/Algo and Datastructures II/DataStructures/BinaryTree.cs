using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace DataStructures
{
    public class BinaryTree<T> where T : IComparable<T>
    {
        public BinaryTree() { }
        public BinaryTree(T initialValue) {
            Root = new BinaryTreeNode<T>(initialValue);
        }

        public BinaryTreeNode<T> Root;
        public int Count { get; private set; }

        public void Add(T value)
        {
            if (Root == null)
            {
                Root = new BinaryTreeNode<T>(value);
                Count++;
            }
            else
            {
                Root.Add(value);
                Count++;
            }
        }

        public void Remove(T value)
        {
            if (Root != null)
            {
                Root = Root.Remove(value);
                Count--;
            }
        }

        public Boolean Contains(T value)
        {
            if (Root != null)
            {
                return Root.Contains(value);
            } else
            {
                return false;
            }
        }

        public void Clear()
        {
            Root = null;
            Count = 0;
        }

        public int Height()
        {
            //I have no clue how to do this one
            //Iterate down the tree, somehow avoid or turn around after short branches, counting how many steps the biggest branch is
            int output = 0;
            int currentDepth = 1;

            if (Root != null)
            {
                output = Root.Height(ref currentDepth, ref output);
            }
            else
            {
                return 0;
            }

            return output;
        }

        public Array ToArray()
        {

            List<T> array = new List<T>();
            string output = "";
            if (Root != null)
            {
                Root.InOrder(array);
            }
            else
            {
                return new int[0];
            }

            return array.ToArray();
        }

        public string InOrder()
        {
            List<T> array = new List<T>(); //instead of a List I could just pass a string with the ref keyword so it isn't passed by value
            string output = "";
            if (Root != null)
            {
                Root.InOrder(array);
            } else
            {
                return string.Empty;
            }

            for (int i = 0; i < array.Count; i++)
            {
                output += array[i].ToString();
                if (i < array.Count - 1) output += ", ";
            }

            return output; 
        }

        public string PreOrder()
        {
            List<T> array = new List<T>();//instead of a List I could just pass a string with the ref keyword so it isn't passed by value
            string output = "";
            if (Root != null)
            {
                Root.PreOrder(array);
            }
            else
            {
                return string.Empty;
            }

            for (int i = 0; i < array.Count; i++)
            {
                output += array[i].ToString();
                if (i < array.Count - 1) output += ", ";
            }

            return output;
        }

        public string PostOrder()
        {
            List<T> array = new List<T>();//instead of a List I could just pass a string with the ref keyword so it isn't passed by value
            string output = "";
            if (Root != null)
            {
                Root.PostOrder(array);
            }
            else
            {
                return string.Empty;
            }

            for (int i = 0; i < array.Count; i++)
            {
                output += array[i].ToString();
                if (i < array.Count - 1) output += ", ";
            }

            return output;
        }
    }

    public class BinaryTreeNode<T> where T : IComparable<T>
    {
        public BinaryTreeNode(T value)
        {
            this.Value = value;
        }

        public T Value;
        public BinaryTreeNode<T> Left;
        public BinaryTreeNode<T> Right;

        public void Add(T value)
        {
            if (value.CompareTo(Value) < 0)
            {
                if (Left == null)
                {
                    Left = new BinaryTreeNode<T>(value);
                }
                else
                {
                    Left.Add(value);
                }
            }
            else if (value.CompareTo(Value) > 0)
            {
                if (Right == null)
                {
                    Right = new BinaryTreeNode<T>(value);
                }
                else
                {
                    Right.Add(value);
                }
            }
            else
            {
                //If value is a duplicate, add to the right
                if (Right == null)
                {
                    Right = new BinaryTreeNode<T>(value);
                }
                else
                {
                    Right.Add(value);
                }
            }
        }
        
        public BinaryTreeNode<T> Remove(T value)
        {
        //No children
        // Tell Parent to null pointer
        //One child
        // Tell Parent to assign child to Left or Right
        //Two children
        //swap the values around, no need for tons of node movements
            //Traverse the tree depending on the value
            if (value.CompareTo(this.Value) < 0)
            {
                Left = Left.Remove(value);
            }
            else if (value.CompareTo(this.Value) > 0)
            {
                Right = Right.Remove(value);
            }
            else
            {
                ////Checking if there's a duplicate to the right that we should deal with //Turns out this makes the duplicate get deleted along side the original, the goal was to only delete the duplicate
                //if (Right != null)
                //{
                //    if (Right.Value.CompareTo(this.Value) == 0)
                //    {
                //        Right = Right.Remove(value);
                //    }
                //}
                //If the right is null, then the left is our child
                if (Right == null)
                {
                    return Left;
                } else if (Left == null) //If the left is null, then the right is our child
                {
                    return Right;
                } else // if neither are null we have a double child scenario
                {
                    //Here we grab the largest number from the lesser side
                    T num = FindMax(Left);
                    //Set this nodes value to that number
                    Value = num;
                    //Then delete the node we grabbed the value from
                    Left = Left.Remove(num);
                    //This is essentially moving the nodes around, but with much less effort and all the same benefits
                }

            }
            return this;
        }

        public T FindMax(BinaryTreeNode<T> startingNode)
        {
            //iterate through right nodes and return biggest
            BinaryTreeNode<T> targetNode = startingNode;
            while (targetNode.Right != null)
            {
                targetNode = targetNode.Right;
                if (targetNode.Right == null)
                {
                    return targetNode.Value;
                }
            }
            return targetNode.Value;
        }

        public T FindMin(BinaryTreeNode<T> startingNode)
        {
            //iterate through left nodes and return smallest
            BinaryTreeNode<T> targetNode = startingNode;
            while (targetNode.Left != null)
            {
                targetNode = targetNode.Left;
                if ( targetNode.Left == null)
                {
                    return targetNode.Value;
                }
            }
            return targetNode.Value;
        }

        public Boolean Contains(T value)
        {
            if (value.CompareTo(Value) < 0)
            {
                if (Left != null)
                {
                    return Left.Contains(value);
                }
            } else if (value.CompareTo(Value) > 0)
            {
                if (Right != null)
                {
                    return Right.Contains(value);
                }
            } else
            {
                return true;
            }
            return false;
        }

        public void InOrder(List<T> array)
        {
            if (Left != null)
            {
                Left.InOrder(array);
            }
            array.Add(this.Value);
            if (Right != null)
            {
                Right.InOrder(array);
            }
            return;
        }

        public void PostOrder(List<T> array)
        {
            if (Left != null)
            {
                Left.PostOrder(array);
            }
            if (Right != null)
            {
                Right.PostOrder(array);
            }
            array.Add(this.Value);
            return;
        }

        public void PreOrder(List<T> array)
        {
            array.Add(this.Value);
            if (Left != null)
            {
                Left.PreOrder(array);
            }
            if (Right != null)
            {
                Right.PreOrder(array);
            }
            return;
        }

        public int Height(ref int currentDepth, ref int maxDepth)
        {
            if (currentDepth > maxDepth)
            {
                maxDepth = currentDepth;
            }
            if (Left != null)
            {
                currentDepth++;
                Left.Height(ref currentDepth,ref maxDepth);
            }
            if (Right != null)
            {
                currentDepth++;
                Right.Height(ref currentDepth,ref maxDepth);
            }
            currentDepth--;
            return maxDepth;
        }
    }
}