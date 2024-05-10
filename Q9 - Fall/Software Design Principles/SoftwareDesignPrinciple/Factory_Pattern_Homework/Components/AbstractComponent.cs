using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Factory_Pattern_Homework
{
    public abstract class AbstractComponent
    {
        public string _backgroundColor { get; set; }
        public string _text { get; set; }
        public int _left { get; set; }
        public int _top { get; set; }
        public int _width { get; set; }
        public int _height { get; set; }

        public AbstractComponent(string bgColor, string text, int left, int top, int width, int height) {
            _backgroundColor = bgColor;
            _text = text;
            _left = left;
            _top = top;
            _width = width;
            _height = height;
        }

        public void PrintMe()
        {
            Console.WriteLine("===================");
            Console.WriteLine(_backgroundColor);
            Console.WriteLine(_text);
            Console.WriteLine(_left);
            Console.WriteLine(_top);
            Console.WriteLine(_width);
            Console.WriteLine(_height);
            Console.WriteLine("===================");

        }
    }
}
