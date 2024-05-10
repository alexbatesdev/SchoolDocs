using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection.Metadata;
using System.Text;
using System.Threading.Tasks;

namespace Decorator_Pattern__Homework_
{
    public
    abstract class FileOperationAbstract
    {
        // This is the next layer down
        protected FileOperationAbstract _subOperation = null;
        protected string _filepath;

        public FileOperationAbstract(FileOperationAbstract subOperation)
        {
            _subOperation = subOperation;
        }

        //Pass the filepath all the way down since I can't figure out how to read the file from the bottom
        public void SetFilePath(string filepath)
        {
            if (_subOperation != null)
            {
                _subOperation.SetFilePath(filepath);
            }
            _filepath = filepath;
        }

        //I originally made these abstract, then I realized that the Write decorators would 
        //all have the same function that simply passes the reading job to the next item or it would do it itself with no modifications
        //Same thing with read decorators not needing modified write functions

        //Abstract methods are like an interface bit on the class
        public virtual string ReadFile()
        {
            //If this is the last stop read the file
            if (_subOperation == null)
            {
                try
                {
                    using (StreamReader reader = new StreamReader(_filepath))
                    {
                        string content = reader.ReadToEnd();
                        return content;
                    }
                }
                catch (Exception e)
                {
                    return e.Message;
                }
            }
            else //Otherwise keep going
            {
                return _subOperation.ReadFile();
            }
        }
        
        //Call the next WriteFile in the chain unless this is the last one in the chain
        public virtual string WriteFile(string contents)
        {
            string temp;
            if (_subOperation != null)
                temp = _subOperation.WriteFile(contents);
            else
                temp = contents;

            return temp;
        }

    }
}
