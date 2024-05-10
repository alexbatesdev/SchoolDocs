using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Command_Pattern_Homework
{
    public class CommandUndo : ICommandHomeWork
    {
        List<ICommandHomeWork> _stack;
        List<ICommandHomeWork> _redoQueue = new List<ICommandHomeWork> { };

        public CommandUndo(List<ICommandHomeWork> _stack) {
            this._stack = _stack;
        }

        public void execute()
        {
            if (_stack.Count >= 1)
            {
                _stack[_stack.Count - 1].undo();
                _redoQueue.Append(_stack[_stack.Count - 1]);
                _stack.RemoveAt(_stack.Count - 1);
            }
            else
            {
                Console.WriteLine("Nothing to undo");
            }
        }

        public void undo()
        {
            if (_redoQueue.Count >= 1)
            {
                _stack.Append(_redoQueue[_redoQueue.Count - 1]);
                _stack[_stack.Count - 1].execute();

            }
            else
            {
                Console.WriteLine("Nothing to redo");
            }
        }

        public string getDisplayText()
        {
            return "Undo";
        }
    }
}
