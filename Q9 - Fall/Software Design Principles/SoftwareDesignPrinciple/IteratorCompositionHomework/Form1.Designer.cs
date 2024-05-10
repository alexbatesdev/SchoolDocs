namespace IteratorCompositionHomework
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.buttonA = new System.Windows.Forms.Button();
            this.displayLabel = new System.Windows.Forms.Label();
            this.buttonB = new System.Windows.Forms.Button();
            this.buttonC = new System.Windows.Forms.Button();
            this.nextButton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // buttonA
            // 
            this.buttonA.Location = new System.Drawing.Point(12, 362);
            this.buttonA.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.buttonA.Name = "buttonA";
            this.buttonA.Size = new System.Drawing.Size(264, 76);
            this.buttonA.TabIndex = 0;
            this.buttonA.Text = "button A";
            this.buttonA.UseVisualStyleBackColor = true;
            // 
            // displayLabel
            // 
            this.displayLabel.AutoSize = true;
            this.displayLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.displayLabel.Location = new System.Drawing.Point(12, 9);
            this.displayLabel.Name = "displayLabel";
            this.displayLabel.Size = new System.Drawing.Size(337, 39);
            this.displayLabel.TabIndex = 5;
            this.displayLabel.Text = "Select Quiz A, B or C";
            // 
            // buttonB
            // 
            this.buttonB.Location = new System.Drawing.Point(283, 362);
            this.buttonB.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.buttonB.Name = "buttonB";
            this.buttonB.Size = new System.Drawing.Size(264, 76);
            this.buttonB.TabIndex = 6;
            this.buttonB.Text = "button B";
            this.buttonB.UseVisualStyleBackColor = true;
            // 
            // buttonC
            // 
            this.buttonC.Location = new System.Drawing.Point(552, 362);
            this.buttonC.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.buttonC.Name = "buttonC";
            this.buttonC.Size = new System.Drawing.Size(264, 76);
            this.buttonC.TabIndex = 7;
            this.buttonC.Text = "button C";
            this.buttonC.UseVisualStyleBackColor = true;
            // 
            // nextButton
            // 
            this.nextButton.Location = new System.Drawing.Point(552, 281);
            this.nextButton.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.nextButton.Name = "nextButton";
            this.nextButton.Size = new System.Drawing.Size(264, 76);
            this.nextButton.TabIndex = 8;
            this.nextButton.Text = "next";
            this.nextButton.UseVisualStyleBackColor = true;
            this.nextButton.Click += new System.EventHandler(this.nextButton_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(828, 450);
            this.Controls.Add(this.nextButton);
            this.Controls.Add(this.buttonC);
            this.Controls.Add(this.buttonB);
            this.Controls.Add(this.displayLabel);
            this.Controls.Add(this.buttonA);
            this.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button buttonA;
        private System.Windows.Forms.Label displayLabel;
        private System.Windows.Forms.Button buttonB;
        private System.Windows.Forms.Button buttonC;
        private System.Windows.Forms.Button nextButton;
    }
}

