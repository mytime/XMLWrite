package com.hello.xmlwrite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * 生成xml文件
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = (TextView) findViewById(R.id.text);

        try {


            //文档生成器工程对象
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            //实例化一个新的文档生成器
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            //生成一个Documen对象
            Document newxml = builder.newDocument();

            //创建一个元素Languages
            Element languages = newxml.createElement("Languages");
            //设置Languages的属性 key:value
            languages.setAttribute("cat","it");

            //创建一个元素lan,设置属性
            Element lan1 = newxml.createElement("lan");
            lan1.setAttribute("id","1");

            //创建一个元素lan,设置内容
            Element name1 = newxml.createElement("name");
            name1.setTextContent("Java");

            //创建一个元素lan,设置内容
            Element ide1 = newxml.createElement("ide");
            ide1.setTextContent("Eclipse");

            //为lan1添加子元素
            lan1.appendChild(name1);
            lan1.appendChild(ide1);

            //为languages添加子元素
            languages.appendChild(lan1);


            //再添加一组内容
            Element lan2 = newxml.createElement("lan");
            lan2.setAttribute("id", "2");
            Element name2 = newxml.createElement("name");
            name2.setTextContent("Swift");
            Element ide2 = newxml.createElement("ide");
            ide2.setTextContent("Xcode");
            lan2.appendChild(name2);
            lan2.appendChild(ide2);
            languages.appendChild(lan2);


            //添加到节点
            newxml.appendChild(languages);

            //准备写入到xml
            //变压器工厂
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            //创建一个变压器
            Transformer transformer = transformerFactory.newTransformer();
            //设置字符集和编码
            transformer.setOutputProperty("encoding", "utf-8");
            //可写的字符集
            StringWriter sw = new StringWriter();
            //开始变压，arg: 源文件，结果集
            transformer.transform(new DOMSource(newxml),new StreamResult(sw));

            //结果显示到TextView中
            text.setText(sw.toString());



        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
