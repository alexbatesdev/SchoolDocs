using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Factory_Pattern_Homework
{
    internal class HTMLLanguageFactory : LanguageFactoryAbstract
    {
        public override string Create(AbstractComponent component)
        {
            switch (component)
            {
                case BoxComponent boxComponent:
                    StringBuilder sbBox = new StringBuilder();
                    sbBox.Append($"<div style='position:absolute;background-color:{boxComponent._backgroundColor};");
                    sbBox.Append($"width:{boxComponent._width}px;height:{boxComponent._height}px;");
                    sbBox.Append($"left:{boxComponent._left}px;top:{boxComponent._top}px;'>\n");
                    sbBox.Append(boxComponent._text + "\n");
                    sbBox.Append("</div>\n");
                    return sbBox.ToString();
                case CheckComponent checkComponent:
                    StringBuilder sbCheckbox = new StringBuilder();
                    sbCheckbox.Append($"<div style='position:absolute;left:{checkComponent._left}px;top:{checkComponent._top}px;background-color:{checkComponent._backgroundColor};width:{component._width}px;height:{component._height}px;' >\n");
                    sbCheckbox.Append($"<input type='checkbox' />\n");
                    sbCheckbox.Append($"{checkComponent._text}\n");
                    sbCheckbox.Append("</div>\n");
                    return sbCheckbox.ToString();
                case ButtonComponent buttonComponent:
                    StringBuilder sbButton = new StringBuilder();
                    sbButton.Append($"<button style='position:absolute;left:{buttonComponent._left}px;top:{buttonComponent._top}px;background-color:{buttonComponent._backgroundColor};width:{component._width}px;height:{component._height}px;'>");
                    sbButton.Append(buttonComponent._text);
                    sbButton.Append("</button>\n");
                    return sbButton.ToString();
                default:
                    StringBuilder sbDefault = new StringBuilder();
                    sbDefault.Append($"<div style='position:absolute;background-color:{component._backgroundColor};background-color:{component._backgroundColor};");
                    sbDefault.Append($"width:{component._width}px;height:{component._height}px;");
                    sbDefault.Append($"left:{component._left}px;top:{component._top}px;'>\n");
                    sbDefault.Append(component._text + "\n");
                    sbDefault.Append("</div>\n");
                    return sbDefault.ToString();
            }
        }
    }
}
