using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Command_Pattern_Homework
{
    public interface ICommandHomeWork
    {
        // Required for command pattern
        void execute();
        void undo();

        // Not required
        string getDisplayText();
    }
}
