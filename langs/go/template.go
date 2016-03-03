package gogen

import (
	"os"
	"text/template"

	"github.com/ezbuy/tgen/tmpl"
)

var tpl *template.Template

func Tpl() *template.Template {
	return tpl
}

func init() {
	tpl = template.New("tgen/golang")

	files := []string{
		"tmpl/golang/include.gogo",
		"tmpl/golang/struct.gogo",
		"tmpl/golang/structs_file.gogo",
		"tmpl/golang/service.gogo",
		"tmpl/golang/services_file.gogo",
		"tmpl/golang/echo_module.gogo",
	}

	for _, filename := range files {
		data, err := tmpl.Asset(filename)
		if err != nil {
			panic(err)
		}

		if _, err = tpl.Parse(string(data)); err != nil {
			panic(err)
		}
	}
}

func outputFile(path string, tplName string, data interface{}) error {
	file, err := os.OpenFile(path, os.O_WRONLY|os.O_CREATE|os.O_TRUNC, 0644)
	if err != nil {
		return err
	}

	defer file.Close()

	return tpl.ExecuteTemplate(file, tplName, data)
}