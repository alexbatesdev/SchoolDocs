using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Decorator_Pattern__Homework_
{
    internal class Dec_UpShift: FileOperationAbstract
    {
        int _shiftAmount = 1;
        bool _onlyShiftLetters = true;

        public Dec_UpShift(FileOperationAbstract subOperation) : base(subOperation)
        {
            _subOperation = subOperation;
        }

        public Dec_UpShift(FileOperationAbstract subOperation, int shiftAmount) : base(subOperation)
        {
            _subOperation = subOperation;
            _shiftAmount = shiftAmount;
        }

        public Dec_UpShift(FileOperationAbstract subOperation, int shiftAmount, bool onlyShiftLetters) : base(subOperation)
        {
            _subOperation = subOperation;
            _shiftAmount = shiftAmount;
            _onlyShiftLetters = onlyShiftLetters;
        }

        public override string WriteFile(string contents)
        {
            string shiftedContents = "";
            //65-90 UPPERCASE
            //97-122 lowercase
            foreach (char chara in contents)
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

                intChar += _shiftAmount;

                if (intChar > 122)
                {
                    intChar -= 26;
                }
                else if (intChar > 90 && intChar < 97)
                {
                    intChar -= 26;
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
