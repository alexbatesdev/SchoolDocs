using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Factory_Pattern_Homework
{
    internal class CheckComponent : AbstractComponent
    {
        public CheckComponent(string bgColor, string text, int left, int top, int width, int height) : base(bgColor, text, left, top, width, height)
        {
        }
    }
}
