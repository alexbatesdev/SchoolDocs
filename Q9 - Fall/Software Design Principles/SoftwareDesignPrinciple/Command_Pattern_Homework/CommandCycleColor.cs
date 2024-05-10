using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Command_Pattern_Homework
{
    public class CommandCycleColor : ICommandHomeWork
    {
        CustomButton _victim;
        int _increment;
        static List<Color> _colors = new List<System.Drawing.Color>() 
        {
            Color.Transparent,
            Color.Red,
            Color.Orange,
            Color.Yellow,
            Color.Green,
            Color.Blue,
            Color.Indigo,
            Color.Violet
        };

        public CommandCycleColor(CustomButton target, int increment) { 
            _victim = target;
            _increment = increment;
        }

        public void execute()
        {
            Color oldColor = _victim.BackColor;
            Console.WriteLine(oldColor);

            int oldIndex = _colors.IndexOf(oldColor);
            if (oldIndex != -1 && oldIndex + 1 < _colors.Count)
            {
                _victim.BackColor = _colors[oldIndex + 1];
            } else
            {
                _victim.BackColor= _colors[1];
            }

        }

        public void undo()
        {
            Color oldColor = _victim.BackColor;

            int oldIndex = _colors.IndexOf(oldColor);
            if (oldIndex != -1 && oldIndex - 1 >= 0)
            {
                _victim.BackColor = _colors[oldIndex - 1];
            }
            else
            {
                _victim.BackColor = _colors[_colors.Count - 1];
            }
        }

        public string getDisplayText()
        {
            return "Cycle Color";
        }

    }
}
