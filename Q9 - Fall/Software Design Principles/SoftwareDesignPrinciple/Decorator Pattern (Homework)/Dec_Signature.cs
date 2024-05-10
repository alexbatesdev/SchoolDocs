using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Decorator_Pattern__Homework_
{
    internal class Dec_Signature: FileOperationAbstract
    {
        protected string _signature;

        public Dec_Signature(FileOperationAbstract subOperation, string signature) : base(subOperation)
        {
            _subOperation = subOperation;
            _signature = signature;
        }

        public override string WriteFile(string contents)
        {
            // Get today and convert to just a date
            DateOnly today = DateOnly.FromDateTime(DateTime.Today);
            // Add our fun formatting
            string output = (contents + " - " + _signature + " | " + today);
            //Keep going deeper or return because we're at the end
            if (_subOperation != null)
                return _subOperation.WriteFile(output);
            else
                return output;
        }
    }
}
