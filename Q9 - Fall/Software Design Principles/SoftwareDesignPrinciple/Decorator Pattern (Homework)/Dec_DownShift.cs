using Microsoft.VisualBasic;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Decorator_Pattern__Homework_
{
    internal class Dec_DownShift : FileOperationAbstract
    {
        //I know the assignment says "A decorator that shifts characters up one value",
        //but I want control over how many values I shift in a way that's easier than stacking decorators
        int _shiftAmount = 1;
        bool _onlyShiftLetters = true;
        public Dec_DownShift(FileOperationAbstract subOperation) : base(subOperation)
        {
        }

        public Dec_DownShift(FileOperationAbstract subOperation, int shiftAmount) : base(subOperation)
        {
            _shiftAmount = shiftAmount;
        }

        public Dec_DownShift(FileOperationAbstract subOperation, int shiftAmount, bool onlyShiftLetters) : base(subOperation)
        {
            _shiftAmount = shiftAmount;
            _onlyShiftLetters |= onlyShiftLetters;
        }

        public Dec_DownShift(FileOperationAbstract subOperation, bool onlyShiftLetters) : base(subOperation)
        {
            _onlyShiftLetters = onlyShiftLetters;
        }


        public override string ReadFile()
        {
            if (_subOperation == null)
            {
                using (StreamReader reader = new StreamReader(_filepath))
                {
                    try
                    {
                        string content = reader.ReadToEnd();
                        return DownShiftLogic(content);
                    }
                    catch (Exception e)
                    {
                        return e.Message;
                    }
                }
            }
            else
            {
                return DownShiftLogic(_subOperation.ReadFile());
            }
        }

        // Seperating it into its own method for readability
        private string DownShiftLogic(string content)
        {
            string shiftedContents = "";
            foreach (char chara in content)
            {
                int intChar = (int)chara;

                if (_onlyShiftLetters)
                {
                    if (chara < 65 || (chara > 90 && chara < 97) || chara > 122)
                    {
                        shiftedContents += chara;
                        continue;
                    }
                }

                intChar -= _shiftAmount;

                if (intChar > 90 && intChar < 97)
                {
                    intChar += 26;
                } else if (intChar < 65)
                {
                    intChar += 26;
                }

                shiftedContents += (char)intChar;
            }


            if (_subOperation != null)
                return _subOperation.WriteFile(shiftedContents);
            else
                return shiftedContents;
        }
    }
}
