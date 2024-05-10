using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Command_Pattern_Remote
{
    internal interface ICommand
    {
        // Required for command pattern
        void execute();
        void undo();

        // Not required for command pattern 
        string getDesc();
    }
}
