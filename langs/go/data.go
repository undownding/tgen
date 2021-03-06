package gogen

import (
	"fmt"
	"path/filepath"
	"sort"

	"github.com/samuel/go-thrift/parser"
)

type definesFileData struct {
	TplUtils

	FilePath string

	Package  string
	Includes [][2]string
	Structs  []*structData
	Services []*serviceData
}

func getDefinesFileData(pkgName, pkgDir string, includes [][2]string, parsed *parser.Thrift) *definesFileData {
	data := &definesFileData{
		FilePath: filepath.Join(pkgDir, "gen_"+pkgName+"_defines.go"),
		Package:  pkgName,
		Includes: includes,
	}

	// structs data
	structs := parsed.Structs
	structNames := make([]string, 0, len(structs))
	for name, _ := range structs {
		structNames = append(structNames, name)
	}

	sort.Strings(structNames)

	for _, structName := range structNames {
		parsedStruct := structs[structName]

		data.Structs = append(data.Structs, &structData{
			Name:   structName,
			Fields: parsedStruct.Fields,
		})
	}

	// services data
	services := parsed.Services
	serviceNames := make([]string, 0, len(services))

	for name, _ := range services {
		serviceNames = append(serviceNames, name)
	}

	sort.Strings(serviceNames)

	for _, serviceName := range serviceNames {
		parsedService := services[serviceName]

		sData := &serviceData{
			Name: serviceName,
		}

		// sort methods
		methodNames := make([]string, 0, len(parsedService.Methods))

		for methodName, _ := range parsedService.Methods {
			methodNames = append(methodNames, methodName)
		}

		sort.Strings(methodNames)

		for _, name := range methodNames {
			sData.Methods = append(sData.Methods, parsedService.Methods[name])
		}

		data.Services = append(data.Services, sData)
	}

	// TODO: enum, const, typedef, exception, ...
	return data
}

type structData struct {
	TplUtils

	Name   string
	Fields []*parser.Field
}

type serviceData struct {
	TplUtils

	Name    string
	Methods []*parser.Method
}

type echoFileData struct {
	TplUtils

	FilePath string

	Package string
	Service *serviceData
}

func getEchoFileData(pkgName, pkgDir string, sData *serviceData) *echoFileData {
	data := &echoFileData{
		FilePath: filepath.Join(pkgDir, fmt.Sprintf("gen_%s_%s_web_apis.go", pkgName, sData.Name)),

		Package: pkgName,
		Service: sData,
	}

	return data
}
