using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Factory_Pattern_Homework
{
    public class ComponentFactory
    {
        public ComponentFactory() { }

        public AbstractComponent Create(string type, string bgColor, string text, int left, int top, int width, int height)
        {
            switch (type)
            {
                case "box":
                    return new BoxComponent(bgColor, text, left, top, width, height);
                case "button":
                    return new ButtonComponent(bgColor, text, left, top, width, height);
                case "checkbox":
                    return new CheckComponent(bgColor, text, left, top, width, height);
                default:
                    return new BoxComponent(bgColor, text, left, top, width, height);
            }
        }
    }
}
